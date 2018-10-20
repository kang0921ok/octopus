package osc.gobaby.octopus.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.octopus.service.admin.server.AdminServerService;
import osc.gobaby.octopus.service.admin.server.entity.AdminServer;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;
import osc.gobaby.octopus.exception.NoMandatoryKeyException;
import osc.gobaby.octopus.service.indexing.IndexingOrderService;
import osc.gobaby.octopus.query.schema.QuerySchemaService;
import osc.gobaby.octopus.query.schema.entity.Query;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/query")
public class QuerySchemaController {

    @Autowired
    private QuerySchemaService querySchemaService;

    @Autowired
    private IndexingOrderService indexingOrderService;

    @Autowired
    private AdminServerService adminServerService;

    /**
     * 사용자가 쿼리 dimension, metric 저장
     */
    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.POST)
    public ApiResponse createQuery(@PathVariable String userId, @RequestBody Query query) throws NoMandatoryKeyException {

        query.setUserId(userId);

        if (querySchemaService.upsertQuery(query)) {

            return ApiResponseFactory.createSuccess(querySchemaService.findQueryForUserIdAndQueryName(userId, query.getQueryName()));
        }

        return ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }

    @ResponseBody
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ApiResponse findQueryListForUserId(@PathVariable String userId) {
        return ApiResponseFactory.createSuccess(querySchemaService.findQueryListForUserId(userId));
    }

    /**
     * druid indexing 시작
     * kafka 정보 리턴
     */
    @ResponseBody
    @RequestMapping(value = "/indexing/create/{userId}/{queryId}", method = RequestMethod.POST)
    public ApiResponse createIndexingJob(@PathVariable String userId, @PathVariable String queryId) {

        AdminServer adminServer = indexingOrderService.createIndexingJob(userId, queryId);

        return adminServer != null ?
                ApiResponseFactory.createSuccess(adminServer) : ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }

    /**
     * druid indexing 제거
     */
    @ResponseBody
    @RequestMapping(value = "/indexing/delete/{userId}/{queryId}", method = RequestMethod.POST)
    public ApiResponse deleteInexingJob(@PathVariable String userId, @PathVariable String queryId) {

        AdminServer adminServer = indexingOrderService.deleteIndexingJob(userId, queryId);

        return adminServer != null ?
                ApiResponseFactory.createSuccess(adminServer) : ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/indexing/kafka/{userId}", method = RequestMethod.GET)
    public ApiResponse initRegistdKafka(@PathVariable String userId) {
        return ApiResponseFactory.createSuccess(adminServerService.findKafkaServer());
    }
}
