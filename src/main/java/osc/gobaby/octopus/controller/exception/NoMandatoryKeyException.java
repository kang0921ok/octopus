package osc.gobaby.octopus.controller.exception;

public class NoMandatoryKeyException extends Exception{

    private String key;

    public NoMandatoryKeyException(String key) {
        super();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "No MandatoryKey : " + key ;
    }
}
