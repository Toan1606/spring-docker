package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.ReferencePersonService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/refperson")
@Transactional

public class ReferencePersonController {
	@Autowired
	private ReferencePersonService referencePersonService;
	
	@GetMapping("/{userid}")
	public ResponseEntity<?> showReferencePerson(@PathVariable Long id){
		User user = referencePersonService.findReferencePersonById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteReferencePerson(@PathVariable Long id){
		User user = referencePersonService.findReferencePersonById(id);
		if(user == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}else {
			referencePersonService.deleteReferencePersonById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}
}
