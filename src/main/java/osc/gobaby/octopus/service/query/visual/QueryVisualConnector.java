package osc.gobaby.octopus.service.query.visual;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;

@Service
public class QueryVisualConnector {
    private static final Logger LOG = Logger.getLogger(QueryVisualConnector.class);

    public String requestNativeQuery(AdminServer druidBrokerServer, String requestBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity entity = new HttpEntity(requestBody, headers);

            ResponseEntity<String> respone = restTemplate.postForEntity(createDruidBrokerUrl(druidBrokerServer), entity, String.class);

            return respone.getBody();
        } catch (Exception e) {
            LOG.error(e);
            return "";
        }
    }

    private String createDruidBrokerUrl(AdminServer druidBrokerServer) {
        return "http://" + druidBrokerServer.getIp() + ":" + druidBrokerServer.getPort() + "/druid/v2?pretty";
    }
}
