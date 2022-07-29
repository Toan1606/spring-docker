package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.RecruiterRegisterService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/recruiterRegister")
public class RecruiterRegisterController {
	
	@Autowired
	RecruiterRegisterService recruiterRegisterService;
	
	@PostMapping("/")
	public ResponseEntity<User> addRecruiter(@RequestBody User user){
		User rs = recruiterRegisterService.addRecruiter(user);
		return new ResponseEntity<User>(rs, HttpStatus.CREATED);
	}
	
//	@PutMapping("/recruiterOnlineCVForm/{id}")
//	public ResponseEntity<User> updateRecruiter(@PathVariable("id") Long id, @RequestBody User user, MultipartFile file){
//		User rs = recruiterRegisterService.updateRecruiter(id, user,file);
//		return new ResponseEntity<User>(rs, HttpStatus.OK);
//	}
	
}
