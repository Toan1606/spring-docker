package com.codedecode.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.LoginRequest;
import com.codedecode.demo.dto.LoginResponse;
import com.codedecode.demo.dto.LogoutResponse;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.dto.Token;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.utils.ResponseMessage;

@RestController
@RequestMapping("/candidate")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class CandidateLoginController {
	
	
	@Autowired
	private AuthService authService;
	
	@GetMapping
	public ResponseEntity<String> hello() {
		return ResponseEntity.status(HttpStatus.OK).body("Hello World");
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginResponse, HttpServletResponse response) {
		LoginResponse login = authService.checkLogin(loginResponse);
		Cookie cookie = new Cookie("refresh_token", login.getAccessToken().getToken());
		cookie.setMaxAge(3600);
		cookie.setHttpOnly(true);
//		cookie.setPath("/api");
		response.addCookie(cookie);
		
		response.setHeader("Authorization", login.getAccessToken().getToken());
		return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.builder().build());
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/refresh")
	public ResponseEntity<Token> refresh(@CookieValue("refresh_token") String refreshToken) {
		String newRefreshToken = authService.refreshAccess(refreshToken).getAccessToken().getToken();
		Token token = Token.builder().token(newRefreshToken).build();
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/logout")
	public ResponseEntity<LogoutResponse> logout(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {
		authService.logout(refreshToken);
		
		Cookie cookie = new Cookie("refresh_token", null);
		cookie.setMaxAge(0);
		cookie.setHttpOnly(true);
		
		response.addCookie(cookie);
		
		return ResponseEntity.status(HttpStatus.OK).body(LogoutResponse.builder().message(ResponseMessage.LOGOUT_SUCCESS.getMessage()).build());
	}
}
