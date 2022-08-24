package com.codedecode.demo.controller;

import com.codedecode.demo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.EmailRequest;

@RestController
@RequestMapping(path = "api/v1/email")
public class EmailController {

	private final EmailService emailService;

	@Autowired
	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping
	public ResponseEntity<String> forgotPassword(@RequestBody EmailRequest email) {
		emailService.sendSimpleEmail(email.getEmail());
		return ResponseEntity.ok().body("Reset Password");
	}
}
