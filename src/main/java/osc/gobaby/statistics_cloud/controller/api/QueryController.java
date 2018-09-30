package osc.gobaby.statistics_cloud.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseType;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.query.QueryService;
import osc.gobaby.statistics_cloud.query.entity.Query;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    /**
     * 사용자가 쿼리 dimension, metric 저장 이후
     * druid indexing 시작
     * kafka 정보 리턴
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ApiResponse createQuery(@PathVariable String userId, @RequestBody Query query) {

        query.setUserId(userId);

        AdminServer adminServer = queryService.startNewQuery(query);

        return  adminServer != null?
                ApiResponseFactory.createSuccess(adminServer) : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }

    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ApiResponse findQueryListForUserId(@PathVariable String userId) {
        return ApiResponseFactory.createSuccess(queryService.findQueryListForUserId(userId));
    }



    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ApiResponse changeQuery(@PathVariable String userId, @RequestBody Query query) throws NoMandatoryKeyException {
        query.setUserId(userId);
        return queryService.modifyQuery(query) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }
}
