package com.codedecode.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AuthService;


@RestController
@RequestMapping("/recruiter")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class RecruiterLoginController {
	
	@Autowired
	private AuthService authService;
	

	
	/*
	 * 
	 *	@author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));
	}
	

}
