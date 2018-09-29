package osc.gobaby.statistics_cloud.controller.api.vo;

public enum ApiResponseType {
    SUCCESS("success",""),
    FAIL("fail",""),
    ALREADY_EXIST("already_exist",""),

    SQL_ERROR("sql_error",""),
    NETWORK_ERROR("network_error","");

    private String code;
    private String message;

    ApiResponseType(String code, String message) {
        this.code = code;
        this.message = message;
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
}
