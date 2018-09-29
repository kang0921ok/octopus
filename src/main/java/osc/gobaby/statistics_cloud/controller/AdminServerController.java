package osc.gobaby.statistics_cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.statistics_cloud.admin.server.AdminServerService;
import osc.gobaby.statistics_cloud.admin.server.entity.AdminServer;
import osc.gobaby.statistics_cloud.controller.exception.NoMandatoryKeyException;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.vo.ApiResponseType;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminServerController {

    @Autowired
    private AdminServerService adminServerService;

    @ResponseBody
    @RequestMapping(value = "/server", method = RequestMethod.GET)
    public ApiResponse findServerList() {
        return ApiResponseFactory.createSuccess(adminServerService.findAdminServerList());
    }

    @ResponseBody
    @RequestMapping(value = "/server", method = RequestMethod.POST)
    public ApiResponse createServer(@RequestBody AdminServer adminServer) {
        return adminServerService.createAdminServer(adminServer) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }

    @ResponseBody
    @RequestMapping(value = "/server", method = RequestMethod.PUT)
    public ApiResponse changeServer(@RequestBody AdminServer adminServer) throws NoMandatoryKeyException {
        return adminServerService.modifyAdminServer(adminServer) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }
}
