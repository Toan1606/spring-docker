package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.RecruiterRegisterDTO;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.service.RecruiterRegisterService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/recruiter-register")
public class RecruiterRegisterController {

	private final  AuthService authService;

	private final RecruiterRegisterService recruiterRegisterService;

	@Autowired
	public RecruiterRegisterController(AuthService authService, RecruiterRegisterService recruiterRegisterService) {
		this.authService = authService;
		this.recruiterRegisterService = recruiterRegisterService;
	}

	@PostMapping("/")
	public ResponseEntity<User> addRecruiter(@RequestBody User user){
		User rs = recruiterRegisterService.addRecruiter(user);
		return new ResponseEntity<User>(rs, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<User> register(@RequestBody RecruiterRegisterDTO registerRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.recruiterRegister(registerRequestDTO));
	}
	
//	@PutMapping("/recruiterOnlineCVForm/{id}")
//	public ResponseEntity<User> updateRecruiter(@PathVariable("id") Long id, @RequestBody User user, MultipartFile file){
//		User rs = recruiterRegisterService.updateRecruiter(id, user,file);
//		return new ResponseEntity<User>(rs, HttpStatus.OK);
//	}
	
}
