package osc.gobaby.octopus.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.octopus.service.admin.db.DbConnectService;
import osc.gobaby.octopus.service.admin.db.entity.DbConnect;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
public class DbConnectController {

    @Autowired
    private DbConnectService dbConnectService;

    @ResponseBody
    @RequestMapping(value = "/api/v1.0/dbConnect", method = RequestMethod.POST)
    public ApiResponse dbConnect(@RequestBody DbConnect dbConnect) {
        return dbConnectService.init(dbConnect) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);

    }
}
