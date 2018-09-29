package osc.gobaby.statistics_cloud.controller.mv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponse;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseFactory;
import osc.gobaby.statistics_cloud.controller.api.vo.ApiResponseType;
import osc.gobaby.statistics_cloud.user.UserService;
import osc.gobaby.statistics_cloud.user.entity.User;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/user")
public class UserViewController {

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String loginView() {
		return "user/login";
	}

    @RequestMapping("/join")
	public String joinView() {
		return "user/join";
	}
}
