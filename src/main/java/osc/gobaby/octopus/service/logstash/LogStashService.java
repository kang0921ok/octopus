package osc.gobaby.octopus.service.logstash;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.octopus.service.admin.server.AdminServerService;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;
import osc.gobaby.octopus.service.logstash.entity.LogStash;
import osc.gobaby.octopus.service.logstash.entity.LogStashInit;
import osc.gobaby.octopus.query.schema.QuerySchemaService;
import osc.gobaby.octopus.service.user.UserService;
import osc.gobaby.octopus.service.user.entity.User;

import java.util.Properties;
import java.util.UUID;

@Service
public class LogStashService {
    private static final Logger LOG = Logger.getLogger(LogStashService.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminServerService adminServerService;

    @Autowired
    private QuerySchemaService querySchemaService;

    public LogStashInit init(String userId, String queryName, String serverHost) {
        User user = userService.findUser(userId);
        String secretKey = user.getSecretKey();

        if (StringUtils.isBlank(secretKey)) {
            secretKey = UUID.randomUUID().toString();
            user.setSecretKey(secretKey);
            userService.updateUser(user);
        }

        LogStashInit logStashInit = new LogStashInit();
        logStashInit.setQueryName(queryName);
        logStashInit.setSecretKey(secretKey);
        logStashInit.setUrl("http://" + serverHost + "/api/v1.0/logstash");

        String example = "curl -X POST \\\n" +
                "  http://{serverHost}/api/v1.0/logstash \\\n" +
                "  -H 'content-type: application/json' \\\n" +
                "  -d '{\n" +
                "\t\"secretKey\" : \"{secretKey}\",\n" +
                "\t\"userId\" : \"{userId}\",\n" +
                "\t\"queryName\" : \"{queryName}\",\n" +
                "\t\"body\" : {\n" +
                "\t\t\"timestamp\" : \"2015-09-12T00:46:58.771Z\",\n" +
                "\t\t\"gender\" : \"man\",\n" +
                "\t\t\"productName\" : \"k1\",\n" +
                "\t\t\"payStatus\" : \"PAY\",\n" +
                "\t\t\"payMethod\" : \"CARD\",\n" +
                "\t\t\"payAmount\" : \"1000\"\n" +
                "\t}\n" +
                "}' ";
        example = example.replace("{secretKey}", secretKey);
        example = example.replace("{userId}", userId);
        example = example.replace("{queryName}", queryName);
        example = example.replace("{serverHost}", serverHost);

        logStashInit.setExample(example);

        return logStashInit;
    }

    public boolean logStash(LogStash logStash) {
        String userId = logStash.getUserId();
        if (userService.authorize(userId, logStash.getSecretKey())) {
            AdminServer kafkaServer = adminServerService.findKafkaServer();
            String url = kafkaServer.getIp() + ":" + kafkaServer.getPort();
            String topic = logStash.getQueryName();

            //@TODO 디멘션 체크
//            Query query = querySchemaService.findQueryForUserIdAndQueryName(userId, queryName);
//            String dimension = query.getDimension();
//            if(!validateDimension(logStash, dimension)){
//                return false;
//            }

            Producer<String, String> producer = createProducer(url);

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String message = objectMapper.writeValueAsString(logStash.getBody());
                producer.send(new ProducerRecord<>(topic, message));

                return true;
            } catch (Exception e) {
                LOG.error(e);
            }

            producer.close();
        }

        return false;
    }

    private boolean validateDimension(LogStash logStash, String dimension) {

        return true;
    }

    private static Producer<String, String> createProducer(String kafkaBrokerUrl) {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaBrokerUrl);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        return new KafkaProducer<>(props);
    }

}
