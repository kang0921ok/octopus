package osc.gobaby.statistics_cloud.controller.exception;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponseType;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({NoMandatoryKeyException.class})
    public ApiResponse sqlError(NoMandatoryKeyException e){
        return ApiResponseFactory.createError(ApiResponseType.SQL_ERROR, e);
    }

    @ResponseBody
    @ExceptionHandler({SQLException.class, BadSqlGrammarException.class})
    public ApiResponse sqlError(Exception e){
        return ApiResponseFactory.createError(ApiResponseType.SQL_ERROR, e);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResponse error(){
        return ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }
}
