package com.codedecode.demo.config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codedecode.demo.interceptor.AuthorizationInterceptor;

public class WebMvcConfig implements WebMvcConfigurer {
	
	private final AuthorizationInterceptor authorizationInterceptor;

	public WebMvcConfig(AuthorizationInterceptor authorizationInterceptor) {
		this.authorizationInterceptor = authorizationInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/api/user");
	}
}
