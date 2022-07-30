package com.codedecode.demo.security.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.codedecode.demo.entity.UserRole;
import com.codedecode.demo.service.JwtUtil;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

	@Value("${application.security.access-token-secret}")
	private String secretKey;

	@Value("${application.security.access-token-secret}")
	private String refreshKey;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer")) {
			// if Authorization header does not exist, then skip this filter
			// and continue to execute next filter class
			filterChain.doFilter(request, response);
			return;
		}

		final String token = authorizationHeader.split(" ")[1].trim();
		if (!JwtUtil.validateAccessToken(token, refreshKey)) {
			// if token is not valid, then skip this filter
			// and continue to execute next filter class.
			// This means authentication is not successful since token is invalid.
			filterChain.doFilter(request, response);
			return;
		}

		// Authorization header exists, token is valid. So, we can authenticate.
		String email = JwtUtil.getSubject(token, secretKey);
		String[] roles = JwtUtil.getRoles(token, secretKey);
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();

		// 1. get authorities for each role
		// 2. add to authorities
		for (String role : roles) {
			switch (role) {
			case "CANDIDATE":
				authorities.addAll(UserRole.CANDIDATE.getGrantedAuthority());
				break;
			case "RECRUITER":
				authorities.addAll(UserRole.RECRUITER.getGrantedAuthority());
				break;
			case "GUEST":
				authorities.addAll(UserRole.GUEST.getGrantedAuthority());
				break;
			}
		}

		// 3. add to usernamepasswordauthenticationtoken
		// initializing UsernamePasswordAuthenticationToken with its 3 parameter
		// constructor
		// because it sets super.setAuthenticated(true); in that constructor.
		UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(email, null, authorities);
		upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		// finally, give the authentication token to Spring Security Context
		SecurityContextHolder.getContext().setAuthentication(upassToken);

		// end of the method, so go for next filter class
		filterChain.doFilter(request, response);

	}

}
