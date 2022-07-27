package com.codedecode.demo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.LoginRequestDTO;
import com.codedecode.demo.dto.LoginResponseDTO;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.service.UserService;

@RestController
@RequestMapping("/recruiter")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class RecruiterLoginController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;
	
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
//	@PostMapping(value = "/login")
//	public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
////		LoginResponseDTO loginResponse = authService.checkLogin(loginRequest);
//		String email = loginRequest.getEmail();
//		User loginUser = userService.getUserByEmail(email);
//		Cookie cookie = new Cookie("refresh_token", loginResponse.getAccessToken().getToken());
//		cookie.setMaxAge(3600);
//		cookie.setHttpOnly(true);
////		cookie.setPath("/login");
//		response.addCookie(cookie);
//		
//		response.setHeader("Authorization", loginResponse.getAccessToken().getToken());
//		return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
//	}
	
//	@PostMapping(value = "/decode")
//	public ResponseEntity<Token> decodeToken(@RequestBody TokenResponse token) {
//		String tokenCharacter = token.getToken();
//		String secretKey = token.getSecretKey();
//		System.out.println(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(tokenCharacter).getBody());
//		return new ResponseEntity<Token>(HttpStatus.OK);
//	}
}
