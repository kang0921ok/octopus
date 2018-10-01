package osc.gobaby.statistics_cloud.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseType;
import osc.gobaby.statistics_cloud.logstash.LogStashService;
import osc.gobaby.statistics_cloud.logstash.entity.LogStash;

@Controller
public class LogStashController {

    @Autowired
    private LogStashService logStashService;

    @ResponseBody
    @RequestMapping(value = "/api/v1.0/logstash/{userId}/{queryName}", method = RequestMethod.POST)
    public ApiResponse logstash(@PathVariable String userId, @PathVariable String queryName, @RequestBody LogStash logstash) {

        return logStashService.logStash(userId, queryName, logstash) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }
}
