package osc.gobaby.statistics_cloud.controller.api.vo;

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

    public static ApiResponse createError(ApiResponseType apiResponseType, Exception e) {
        return new ApiResponse(apiResponseType.getCode(), apiResponseType.getMessage(), e.getMessage());
    }

    private static ApiResponse createApiResponse(String code, String message, Object body) {
        return new ApiResponse(code, message, body);
    }
}
