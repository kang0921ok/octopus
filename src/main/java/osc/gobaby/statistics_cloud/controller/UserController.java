package osc.gobaby.statistics_cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import osc.gobaby.statistics_cloud.user.User;
import osc.gobaby.statistics_cloud.user.UserService;

import java.util.List;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/userList")
    public List<User> user() {
        return userService.findUserList();
    }

}
