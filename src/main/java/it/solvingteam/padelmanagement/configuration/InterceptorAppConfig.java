package it.solvingteam.padelmanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import it.solvingteam.padelmanagement.interceptor.Interceptor;

@SuppressWarnings("deprecation")
@Component
public class InterceptorAppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private Interceptor interceptor;
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) { 
		registry.addInterceptor(interceptor);
	}

}
