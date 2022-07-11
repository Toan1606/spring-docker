package com.codedecode.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.codedecode.demo.exception.NoBearerTokenError;
import com.codedecode.demo.service.AuthService;

public class AuthorizationInterceptor implements HandlerInterceptor {
	
	private final AuthService authService;

	public AuthorizationInterceptor(AuthService authService) {
		this.authService = authService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String authorizationHeader = request.getHeader("Authorization");

		if (authorizationHeader == null) {
			throw new NoBearerTokenError();
		}

		request.setAttribute("user", authService.getUserFromToken(authorizationHeader));
		return true;
	}
}
