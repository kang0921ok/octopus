package osc.gobaby.statistics_cloud.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.StatisticsConnectTestService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseType;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/admin")
public class AdminServerController {

    @Autowired
    private AdminServerService adminServerService;

    @Autowired
    private StatisticsConnectTestService connectTestService;

    @ResponseBody
    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public ApiResponse findServerList() {
        return ApiResponseFactory.createSuccess(adminServerService.findAdminServerList());
    }

    @ResponseBody
    @RequestMapping(value = "/server/druid/broker", method = RequestMethod.GET)
    public ApiResponse findDruidBrokerServer() {
        return ApiResponseFactory.createSuccess(adminServerService.findDruidBrokerServer());
    }

    @ResponseBody
    @RequestMapping(value = "/server", method = RequestMethod.POST)
    public ApiResponse createServer(@RequestBody AdminServer adminServer) throws NoMandatoryKeyException {
        boolean isConnectTest = connectTestService.connectTest(adminServer);
        return isConnectTest && adminServerService.upsertAdminServer(adminServer) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }
}
