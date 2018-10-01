package osc.gobaby.statistics_cloud.indexing;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;

@Service
public class IndexingOrderConnector {
    private static final Logger LOG = Logger.getLogger(IndexingOrderConnector.class);

    public boolean requestCreateIndexingJob(AdminServer overlordServer, String requestBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity entity = new HttpEntity(requestBody, headers);

            ResponseEntity<String> respone = restTemplate.postForEntity(createOverlordUrl(overlordServer), entity, String.class);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }

        return true;
    }

    public boolean requestDeleteIndexingJob(AdminServer overlordServer, String queryName) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity entity = new HttpEntity(null, headers);

            String url = createDeleteIndexingJobUrl(overlordServer, queryName);
            ResponseEntity<String> respone = restTemplate.postForEntity(url, entity, String.class);
        } catch (Exception e) {
            LOG.error(e);
            return false;
        }

        return true;
    }

    private String createOverlordUrl(AdminServer overlordServer) {
        return "http://" + overlordServer.getIp() + ":" + overlordServer.getPort() + "/druid/indexer/v1/supervisor";
    }

    private String createDeleteIndexingJobUrl(AdminServer overlordServer, String supervisorId) {
        return "http://" + overlordServer.getIp() + ":" + overlordServer.getPort() + "/druid/indexer/v1/supervisor/"+ supervisorId + "/shutdown";
    }
}
