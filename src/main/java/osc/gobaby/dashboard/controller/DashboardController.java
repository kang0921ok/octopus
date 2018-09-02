package osc.gobaby.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import osc.gobaby.dashboard.service.DashboardService;

@RestController
public class DashboardController {
	
	@Autowired
	DashboardService dashboardService;
	
	@ResponseBody
	@RequestMapping("/")
	public String root() {
		return "Hello World";
	}
	
}
