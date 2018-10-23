package osc.gobaby.octopus.controller.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import osc.gobaby.octopus.controller.api.vo.ApiResponse;
import osc.gobaby.octopus.controller.api.vo.ApiResponseFactory;
import osc.gobaby.octopus.controller.api.vo.ApiResponseType;
import osc.gobaby.octopus.service.user.UserService;
import osc.gobaby.octopus.service.user.entity.User;
import osc.gobaby.octopus.support.UserAuthenticationProvider;

/**
 * Created by ShinHyun.Kang on 2018. 9. 9..
 */
@Controller
@RequestMapping(value = "/api/v1.0/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserAuthenticationProvider authenticationProvider;
    
    @ResponseBody
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public ApiResponse login(@RequestBody User user, HttpServletRequest request,
    						 HttpServletResponse response) {
    	
    	UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            Authentication auth = authenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return ApiResponseFactory.createSuccess();
        } catch (BadCredentialsException ex) {
            return ApiResponseFactory.createFail(ApiResponseType.FAIL);
        }
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
