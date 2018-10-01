package osc.gobaby.statistics_cloud.logstash;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.logstash.entity.LogStash;
import osc.gobaby.statistics_cloud.query.schema.QuerySchemaService;
import osc.gobaby.statistics_cloud.query.schema.entity.Query;
import osc.gobaby.statistics_cloud.user.UserService;

import java.util.Properties;

@Service
public class LogStashService {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminServerService adminServerService;

    @Autowired
    private QuerySchemaService querySchemaService;

    public boolean logStash(String userId, String queryName, LogStash logStash) {
        if (userService.authorize(userId, logStash.getSecretKey())) {
            AdminServer kafkaServer = adminServerService.findKafkaServer();
            String url = kafkaServer.getIp() + ":" + kafkaServer.getPort();
            String topic = queryName;

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
            } catch (Exception e){
            }

            producer.close();
        }

        return false;
    }

    private boolean validateDimension(LogStash logStash, String dimension){

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
