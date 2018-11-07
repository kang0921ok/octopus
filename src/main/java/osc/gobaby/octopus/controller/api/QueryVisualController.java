package osc.gobaby.octopus.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.service.query.visual.QueryVisualService;
import osc.gobaby.octopus.service.query.visual.entity.ApiQueryRequest;
import osc.gobaby.octopus.service.query.visual.entity.NativeQuery;
import osc.gobaby.octopus.service.query.visual.entity.SqlQuery;

import java.security.Principal;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/query")
public class QueryVisualController {

    @Autowired
    private QueryVisualService queryVisualService;

    /**
     * visual native query
     */
    @ResponseBody
    @RequestMapping(value = "/visual/{userId}/nativeQuery", method = RequestMethod.POST)
    public ApiResponse nativeQuery(@PathVariable String userId, @RequestBody NativeQuery nativeQuery, Principal principal) {
        return ApiResponseFactory.createSuccess(queryVisualService.reqeustNativeQuery(principal.getName(), nativeQuery.getNativeQuery()));
    }

    /**
     * visual sql query
     */
    @ResponseBody
    @RequestMapping(value = "/visual/{userId}/sqlQuery", method = RequestMethod.POST)
    public ApiResponse sqlQuery(@PathVariable String userId, @RequestBody SqlQuery sqlQuery, Principal principal) {
        return ApiResponseFactory.createSuccess(queryVisualService.reqeustSqlQuery(principal.getName(), sqlQuery.getSqlQuery()));
    }

    /**
     * API native query
     */
    @ResponseBody
    @RequestMapping(value = "/nativeQuery", method = RequestMethod.POST)
    public ApiResponse apiNativeQuery(@RequestBody ApiQueryRequest apiQueryRequest) {
        return ApiResponseFactory.createSuccess(queryVisualService.reqeustNativeQuery(apiQueryRequest.getUserId(), apiQueryRequest.getQuery()));
    }

    /**
     * API sql query
     */
    @ResponseBody
    @RequestMapping(value = "/sqlQuery", method = RequestMethod.POST)
    public ApiResponse apiSqlQuery(@RequestBody ApiQueryRequest apiQueryRequest) {
        return ApiResponseFactory.createSuccess(queryVisualService.reqeustSqlQuery(apiQueryRequest.getUserId(), apiQueryRequest.getQuery()));
    }


}
