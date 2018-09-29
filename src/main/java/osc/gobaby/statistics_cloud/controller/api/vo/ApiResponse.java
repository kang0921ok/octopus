package osc.gobaby.statistics_cloud.controller.api.vo;

public class ApiResponse<T> {

    private String code;
    private String message;
    private T body;

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
