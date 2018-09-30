package osc.gobaby.statistics_cloud.indexing.entity;

public class IoConfig {
    private String topic;
    private ConsumerProperties consumerProperties;
    private long taskCount;
    private long replicas;
    private String taskDuration;

    public IoConfig(String topic, String kafkaBroker) {
        this.topic = topic;
        this.consumerProperties = new ConsumerProperties(kafkaBroker);
        this.taskCount = 1;
        this.replicas = 1;
        this.taskDuration = "PT3M";
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ConsumerProperties getConsumerProperties() {
        return consumerProperties;
    }

    public void setConsumerProperties(ConsumerProperties consumerProperties) {
        this.consumerProperties = consumerProperties;
    }

    public long getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(long taskCount) {
        this.taskCount = taskCount;
    }

    public long getReplicas() {
        return replicas;
    }

    public void setReplicas(long replicas) {
        this.replicas = replicas;
    }

    public String getTaskDuration() {
        return taskDuration;
    }

    public void setTaskDuration(String taskDuration) {
        this.taskDuration = taskDuration;
    }

    static class ConsumerProperties{
        private String bootstrapServers;

        public ConsumerProperties(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
        }

        public String getBootstrapServers() {
            return bootstrapServers;
        }

        public void setBootstrapServers(String bootstrapServers) {
            this.bootstrapServers = bootstrapServers;
        }
    }
}
