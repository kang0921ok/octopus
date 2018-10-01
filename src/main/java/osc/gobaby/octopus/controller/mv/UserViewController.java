package osc.gobaby.octopus.controller.mv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/user")
public class UserViewController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginView() {
        return "user/login";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String joinView() {
        return "user/join";
    }
}
