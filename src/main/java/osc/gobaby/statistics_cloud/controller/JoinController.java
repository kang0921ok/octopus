package osc.gobaby.statistics_cloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JoinController {
	
	@RequestMapping("/join")
    public String root() {
        return "join";
    }
	
}