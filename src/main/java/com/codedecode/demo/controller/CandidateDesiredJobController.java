package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.DesiredJobDTO;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/desiredjob")
@Transactional
public class CandidateDesiredJobController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showDesiredJob(@PathVariable Long userId){
		User user = userService.findUserById(userId);
		DesiredJob desiredJob = user.getDesiredJob();
		DesiredJobDTO dDTO = new DesiredJobDTO();
		dDTO.setId(desiredJob.getId());
		dDTO.setMajor(desiredJob.getName());
		dDTO.setWorkingForm(desiredJob.getWorkingForm().getName());
		dDTO.setYearOfExp(desiredJob.getYearOfExperience().getName());
		dDTO.setRank(desiredJob.getRank().getName());
		dDTO.setSalary(desiredJob.getSalary().getName());
		dDTO.setUserId(userId);
//		List<Address> address = desiredJob.getAddresss();
		dDTO.setAddress(desiredJob.getAddresss());
		
		return new ResponseEntity<>(dDTO, HttpStatus.OK);
	}
}
