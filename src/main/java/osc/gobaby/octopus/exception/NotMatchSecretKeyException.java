package osc.gobaby.octopus.exception;

public class NotMatchSecretKeyException extends NotMatchException {

    public NotMatchSecretKeyException(String key) {
        super(key);
    }

    @Override
    public String getMessage() {
        return "Not Match SecretKey : " + super.getKey();
    }
}
