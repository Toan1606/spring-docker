package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/add")
	public ResponseEntity<User> addNewPosting() {
		userService.addNewUser(null);
		return new ResponseEntity<User>(User.builder().build(), HttpStatus.CREATED);	
	}
}
