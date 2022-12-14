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
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.service.CandidateRegisterService;
import com.codedecode.demo.service.DesiredJobService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/candidate-online-cv-form")
public class CandidateOnlineCVFormController {

	private final CandidateRegisterService candidateRegisterService;

	private final UserService userService;

	private final DesiredJobService desiredJobService;

	@Autowired
	public CandidateOnlineCVFormController(CandidateRegisterService candidateRegisterService, UserService userService, DesiredJobService desiredJobService) {
	this.candidateRegisterService = candidateRegisterService;
	this.userService = userService;
	this.desiredJobService = desiredJobService;
	}

	@GetMapping("/")
	public ResponseEntity<CandidateOnlineFormDTO> getAllCombobox(){
		List<YearOfExperience> expList = candidateRegisterService.getAllYearOfExperience();
		List<WorkingForm> workingFormList = candidateRegisterService.getAllWorkingForm();
//		List<Salary> salaryList = candidateRegisterService.getAllSalary();
		
		CandidateOnlineFormDTO rs = CandidateOnlineFormDTO.builder().yearOfExperience(expList).workingForm(workingFormList).build();
		
		return new ResponseEntity<CandidateOnlineFormDTO>(rs, HttpStatus.OK);
	}
	
	@PutMapping(value="/{candidateId}") 
	public ResponseEntity<User> updateCandidate(@PathVariable("candidateId") Long id, @RequestBody User user){	
		User rs = userService.findUserById(id);
		if(rs!=null) {
			rs.setBirthDate(user.getBirthDate());
			rs.setGender(user.getGender());
			rs.setMariaStatus(user.getMariaStatus());
			rs.setCareerGoals(user.getCareerGoals());
			rs.setDescription(user.getDescription());
			rs.setUniversity(user.getUniversity());
			rs.setRating(user.getRating());
			userService.updateCandidateOnlineCVForm();
			
			DesiredJob desiredJob = new DesiredJob();
			Salary salary = new Salary();
			WorkingForm form = new WorkingForm();
			YearOfExperience experience = new YearOfExperience();
			salary.setId(7L);
			salary.setName("15 - 20 tri???u");
			form.setId(3L);
			form.setName("B??n th???i gian ");
			experience.setId(2L);
			experience.setName("0 - 1 n??m");
			desiredJob.setSalary(salary);
			desiredJob.setWorkingForm(form);
			desiredJob.setYearOfExperience(experience);
			desiredJobService.updateFormOnline(rs.getId(), form, experience, salary);

			return new ResponseEntity<>(HttpStatus.OK);
		}	
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
}
