package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.CandidateOnlineFormDTO;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.service.CandidateRegisterService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/candidateOnlineCVForm")
public class CandidateOnlineCVFormController {
	
	@Autowired
	CandidateRegisterService candidateRegisterService;
	
	@Autowired
	UserService service;
	
	@Autowired
	UserRepository repository;
	
	@GetMapping("/")
	public ResponseEntity<CandidateOnlineFormDTO> getAllCombobox(){
		List<YearOfExperience> expList = candidateRegisterService.getAllYearOfExperience();
		List<WorkingForm> workingFormList = candidateRegisterService.getAllWorkingForm();
//		List<Salary> salaryList = candidateRegisterService.getAllSalary();
		
		CandidateOnlineFormDTO rs = CandidateOnlineFormDTO.builder().yearOfExperience(expList).workingForm(workingFormList).build();
		
		return new ResponseEntity<CandidateOnlineFormDTO>(rs, HttpStatus.OK);
	}
	
	@PutMapping(value="/{candidateId}") 
	public ResponseEntity<User> updateCandidate(@PathVariable("candidateId") int id, @RequestBody User user){	
		User rs = repository.getUserById(id);
		if(rs!=null) {
			rs.setBirthDate(user.getBirthDate());
			rs.setGender(user.getGender());
			rs.setMariaStatus(user.getMariaStatus());
			rs.setCareerGoals(user.getCareerGoals());
			rs.setDescription(user.getDescription());
			rs.setUniversity(user.getUniversity());
			rs.setRating(user.getRating());
			service.updateCandidateOnlineCVForm();
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
}
