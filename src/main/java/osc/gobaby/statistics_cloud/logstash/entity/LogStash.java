package osc.gobaby.statistics_cloud.logstash.entity;

import java.util.Map;

public class LogStash {
    private String secretKey;
    private Map<String, String> body;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Map<String, String> getBody() {
        return body;
    }

    public void setBody(Map<String, String> body) {
        this.body = body;
    }
}
