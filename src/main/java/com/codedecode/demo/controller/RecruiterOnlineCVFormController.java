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

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/recruiter-online-cv-form")
public class RecruiterOnlineCVFormController {

	private final  UserService userService;

	private final AddressService addressService;

	public RecruiterOnlineCVFormController(UserService userService, AddressService addressService) {
		this.userService = userService;
		this.addressService = addressService;
	}
	
	@PutMapping(value="/{recruiterId}") 
	public ResponseEntity<User> updateRecuiter(@PathVariable("recruiterId") Long id, @RequestBody User user){	
		User rs = userService.findUserById(id);
		Address address = addressService.findAddressByProvinceAndCity(1L, 1L);
		if(rs!=null) {
			rs.setImages(user.getImages());
			rs.setPhone(user.getPhone());
			rs.setTaxtNumber(user.getTaxtNumber());
			rs.setDescription(user.getDescription());
			rs.setRecruiterDescription(user.getDescription()); 
			rs.setAddress(address);
			userService.updateCandidateOnlineCVForm();
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
}
