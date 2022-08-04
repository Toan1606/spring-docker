package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.EmailRequest;
import com.codedecode.demo.service.email.EmailServiceImpl;

@RestController
@RequestMapping(path = "/email")
public class EmailController {
	
	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@GetMapping
	public ResponseEntity<String> forgotPassword(@RequestBody EmailRequest email) {
		emailServiceImpl.sendSimpleEmail(email.getEmail());
		return ResponseEntity.ok().body("Reset Password");
	}
}
