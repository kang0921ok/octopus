package osc.gobaby.octopus.exception;

public class NotMatchException extends Exception{

    private String key;

    public NotMatchException(String key) {
        super();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
