package osc.gobaby.statistics_cloud.controller.mv;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardViewController {
	
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
	
}
