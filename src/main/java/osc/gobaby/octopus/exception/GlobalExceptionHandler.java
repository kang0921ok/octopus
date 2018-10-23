package osc.gobaby.octopus.exception;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;

import java.sql.SQLException;

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
