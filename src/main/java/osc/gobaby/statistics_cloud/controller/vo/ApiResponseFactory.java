package osc.gobaby.statistics_cloud.controller.vo;

public class ApiResponseFactory {
    public static ApiResponse createSuccess() {
        ApiResponseType apiResponseType = ApiResponseType.SUCCESS;
        return new ApiResponse(apiResponseType.getCode(), apiResponseType.getMessage());
    }

    public static ApiResponse createSuccess(Object body) {
        ApiResponseType apiResponseType = ApiResponseType.SUCCESS;
        return new ApiResponse(apiResponseType.getCode(), apiResponseType.getMessage(), body);
    }

    public static ApiResponse createFail(ApiResponseType apiResponseType) {
        return new ApiResponse(apiResponseType.getCode(), apiResponseType.getMessage());
    }

    private static ApiResponse createApiResponse(String code, String message, Object body) {
        return new ApiResponse(code, message, body);
    }
}
