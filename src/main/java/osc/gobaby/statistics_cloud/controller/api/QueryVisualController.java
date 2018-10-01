package osc.gobaby.statistics_cloud.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.StatisticsConnectTestService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseType;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.query.visual.QueryVisualService;
import osc.gobaby.statistics_cloud.query.visual.entity.NativeQuery;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/query/visual")
public class QueryVisualController {

    @Autowired
    private QueryVisualService queryVisualService;

    @ResponseBody
    @RequestMapping(value = "/{userId}/nativeQuery", method = RequestMethod.POST)
    public ApiResponse nativeQuery(@PathVariable String userId, @RequestBody NativeQuery nativeQuery) {
        return ApiResponseFactory.createSuccess(queryVisualService.reqeustNativeQuery(userId, nativeQuery.getNativeQuery()));
    }
}
