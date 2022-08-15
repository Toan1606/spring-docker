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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.DesireJobRequestDTO;
import com.codedecode.demo.dto.DesiredJobDTO;
import com.codedecode.demo.dto.UpdateDesireJobRequestDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.DesiredJobService;
import com.codedecode.demo.service.PostingCategoryService;
import com.codedecode.demo.service.RankService;
import com.codedecode.demo.service.SalaryService;
import com.codedecode.demo.service.UserService;
import com.codedecode.demo.service.WorkExperienceService;
import com.codedecode.demo.service.WorkingFormService;
import com.codedecode.demo.service.YearOfExperienceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/desiredjob")
@Transactional
public class CandidateDesiredJobController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private DesiredJobService desiredJobService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private WorkingFormService workingFormService;
	
	@Autowired
	private YearOfExperienceService yearOfExperienceService;
	
	@Autowired
	private PostingCategoryService postingCategoryService;

	@Autowired
	private SalaryService salaryService;

	@GetMapping("/{userId}")
	public ResponseEntity<DesiredJobDTO> showDesiredJob(@PathVariable Long userId) {
		System.out.println("user id : " + userId);
		User user = userService.findUserById(userId);
		DesiredJob desiredJob = user.getDesiredJob();

		DesiredJobDTO dDTO = new DesiredJobDTO();
		String jobName = desiredJob.getName();
		WorkingForm workingForm = desiredJob.getWorkingForm();
		YearOfExperience yearOfExperience = desiredJob.getYearOfExperience();
		Rank rank = desiredJob.getRank();
		Salary salary = desiredJob.getSalary();
		Collection<Address> addresss = desiredJob.getAddresss();

		dDTO.setId(desiredJob.getId());
		dDTO.setJobName(jobName);
		dDTO.setWorkingForm(workingForm.getName());
		dDTO.setWorkingFormId(workingForm.getId());
		dDTO.setYearOfExp(yearOfExperience.getName());
		dDTO.setYearOfExpId(yearOfExperience.getId());
		dDTO.setRank(rank.getName());
		dDTO.setRankId(rank.getId());
		dDTO.setSalary(salary.getName());
		dDTO.setSalaryId(salary.getId());
		dDTO.setUserId(userId);
		dDTO.setAddress(addresss);


		return new ResponseEntity<DesiredJobDTO>(dDTO, HttpStatus.OK);
	}
	
//	@GetMapping("/edit/{userId}")
//	public ResponseEntity<?> getDesiredJob(@PathVariable Long userId){
//		User user = userService.findUserById(userId);
//		DesiredJob desiredJob = user.getDesiredJob();
//		
//		DesireJobRequestDTO dDTO = new DesireJobRequestDTO();
//		dDTO.setId(desiredJob.getId());
//		dDTO.setJobName(desiredJob.getName());
//		dDTO.setSalary(desiredJob.getSalary());
//		dDTO.setRank(desiredJob.getRank());
//		dDTO.setYearOfExperience(desiredJob.getYearOfExperience());
//		dDTO.setWorkingForm(desiredJob.getWorkingForm());
//		dDTO.setAddress(desiredJob.getAddresss());
//		
//		return new ResponseEntity<DesireJobRequestDTO>(dDTO, HttpStatus.OK);
//	}

	@PostMapping("/update")
	public ResponseEntity<DesiredJob> updateDesiredJob(@RequestBody UpdateDesireJobRequestDTO request) {
		System.out.println(request.toString() + " asdasdadasd");
		// 1. get id
		Long desiredJobId = request.getDesiredId();
		Long rankId = request.getRankId();
		Long workingFormId = request.getWorkingFormId();
		Long yearOfExperienceId = request.getYearOfExperienceId();
		Long salaryId = request.getSalaryId();
		List<Long> addresssId = request.getAddresssId();
		Long postingCategoryId = request.getPostingCategoryId();
		
		// 2. get object by id
		DesiredJob desiredJob = desiredJobService.findById(desiredJobId);
		Rank rank = rankService.findById(rankId);
		WorkingForm workingForm = workingFormService.findById(workingFormId);
		YearOfExperience yearOfExperience = yearOfExperienceService.findYearOfExperienceById(yearOfExperienceId);
		Salary salary = salaryService.findSalaryById(salaryId);
		
		List<Address> addresss = new ArrayList<Address>();
		Address address = null;
		for (Long addressId : addresssId) {
			address = addressService.findAddressById(addressId);
			addresss.add(address);
		}
		
		PostingCategory postingCategory = postingCategoryService.findById(postingCategoryId);
	
		// 3. update desired job
		desiredJob = desiredJobService.update(desiredJob, request.getDesiredJobName(), rank, workingForm, yearOfExperience, salary, addresss, postingCategory);
		return  new ResponseEntity<DesiredJob>(desiredJob, HttpStatus.OK);
	}
	
}
