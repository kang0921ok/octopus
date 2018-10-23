package osc.gobaby.octopus.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;
import osc.gobaby.octopus.service.logstash.LogStashService;
import osc.gobaby.octopus.service.logstash.entity.LogStash;
import osc.gobaby.octopus.service.logstash.entity.LogStashInit;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogStashController {

    @Autowired
    private LogStashService logStashService;

    @ResponseBody
    @RequestMapping(value = "/api/v1.0/logstash", method = RequestMethod.POST)
    public ApiResponse logstash(@RequestBody LogStash logstash) {

        return logStashService.logStash(logstash) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }

    @ResponseBody
    @RequestMapping(value = "/api/v1.0/logstash/{userId}/{queryName}", method = RequestMethod.GET)
    public ApiResponse logstash(@PathVariable String userId, @PathVariable String queryName, HttpServletRequest httpServletRequest) {

        LogStashInit logStashInit = logStashService.init(
                userId,
                queryName,
                httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort()
        );

        return logStashInit != null ?
                ApiResponseFactory.createSuccess(logStashInit) : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }
}
