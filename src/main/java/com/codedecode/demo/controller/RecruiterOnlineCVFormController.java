package com.codedecode.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/recruiterOnlineCVForm")
public class RecruiterOnlineCVFormController {
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
	
	@PutMapping(value="/{recruiterId}") 
	public ResponseEntity<User> updateRecuiter(@PathVariable("recruiterId") int id, @RequestBody User user){	
		User rs = repository.getUserById(id);
		if(rs!=null) {
			rs.setImages(user.getImages());
			rs.setPhone(user.getPhone());
			rs.setAddressName(user.getAddressName());
			rs.setTaxtNumber(user.getTaxtNumber());
			rs.setDescription(user.getDescription()); 
			service.updateCandidateOnlineCVForm();
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
}
