package com.codedecode.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/desiredjob")
@Transactional
public class CandidateDesiredJobController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<DesiredJobDTO> showDesiredJob(@PathVariable Long userId) {
//		System.out.println("user id : " + userId);
		User user = userService.findUserById(userId);
		DesiredJob desiredJob = user.getDesiredJob();

		DesiredJobDTO dDTO = new DesiredJobDTO();
		String major = desiredJob.getName();
		WorkingForm workingForm = desiredJob.getWorkingForm();
		YearOfExperience yearOfExperience = desiredJob.getYearOfExperience();
		Rank rank = desiredJob.getRank();
		Salary salary = desiredJob.getSalary();
		Collection<Address> addresss = desiredJob.getAddresss();

		dDTO.setId(desiredJob.getId());
		dDTO.setMajor(major);
		dDTO.setWorkingForm(workingForm);
		dDTO.setYearOfExp(yearOfExperience);
		dDTO.setRank(rank);
		dDTO.setSalary(salary);
		dDTO.setUserId(userId);
		dDTO.setAddress(addresss);

		return new ResponseEntity<DesiredJobDTO>(dDTO, HttpStatus.OK);
	}
//	@GetMapping("/edit")
//	public ResponseEntity<DesiredJobDTO> getDesiredJobByUserId(@PathVariable Long userId) {
//		
//	}
}
