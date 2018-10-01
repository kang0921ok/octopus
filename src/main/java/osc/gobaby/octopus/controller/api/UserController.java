package osc.gobaby.octopus.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;
import osc.gobaby.octopus.user.entity.User;
import osc.gobaby.octopus.user.UserService;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ApiResponse login(@RequestBody User user) {
        return userService.login(user) ?
                ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/join" , method = RequestMethod.POST)
    public ApiResponse join(@RequestBody User user) {
        boolean isDuplicate = userService.isDuplicate(user);
        if(isDuplicate){
            return ApiResponseFactory.createFail(ApiResponseType.ALREADY_EXIST);
        } else {
            return userService.join(user) ?
                    ApiResponseFactory.createSuccess() : ApiResponseFactory.createFail(ApiResponseType.FAIL);
        }
    }
}
