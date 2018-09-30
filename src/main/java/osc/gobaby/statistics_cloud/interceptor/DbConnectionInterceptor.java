package osc.gobaby.statistics_cloud.interceptor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import osc.gobaby.statistics_cloud.admin.db.DbConnectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DbConnectionInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = Logger.getLogger(DbConnectionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
							 HttpServletResponse response,
							 Object handler) {
		if(!DbConnectUtils.isCreatedDbConnectMetaFile()){
			try {
				response.sendRedirect("/gate");
			} catch (Exception e){
				LOG.error(e);
			}
		}

		return true;
	}
	
	@Override
	public void postHandle( HttpServletRequest request,
							HttpServletResponse response,
							Object handler,
							ModelAndView modelAndView) {
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
								HttpServletResponse response, 
								Object handler, 
								Exception ex) {
	}
}