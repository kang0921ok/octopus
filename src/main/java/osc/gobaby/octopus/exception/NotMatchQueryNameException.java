package osc.gobaby.octopus.exception;

public class NotMatchQueryNameException extends NotMatchException{

    public NotMatchQueryNameException(String key) {
        super(key);
    }

    @Override
    public String getMessage() {
        return "Not Match Query name : " + super.getKey() ;
    }
}
