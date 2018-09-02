package osc.gobaby.dashboard.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
	
	private static final Logger logger = LoggerFactory.getLogger(DashboardService.class);
	
	public void doSomething() {
		String value = "##";
		logger.trace("doStuff needed more information - {}", value);
		logger.debug("doStuff needed to debug - {}", value);
		logger.info("doStuff took input - {}", value);
		logger.warn("doStuff needed to warn - {}", value);
		logger.error("doStuff encountered an error with value - {}", value);
	}
	
}
