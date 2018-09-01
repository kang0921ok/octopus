package osc.gobaby.statistics_cloud;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@ResponseBody
	@RequestMapping("/")
	public String root() {
		return "Hello World";
	}
	
}
