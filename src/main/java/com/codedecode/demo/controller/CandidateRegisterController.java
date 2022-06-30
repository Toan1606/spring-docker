package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.CandidateRegisterService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/candidateRegister")
public class CandidateRegisterController {
	
	@Autowired
	CandidateRegisterService candidateRegisterService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<User> addCandidate(@RequestBody User user){
		User rs = candidateRegisterService.addCandidate(user);
		return new ResponseEntity<User>(rs, HttpStatus.CREATED);
	}
	
}
