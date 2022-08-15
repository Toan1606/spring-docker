package com.codedecode.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.DesiredJobRequestDTO;
import com.codedecode.demo.dto.UpdateDesireJobRequestDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.DesiredJobService;
import com.codedecode.demo.service.PostingCategoryService;
import com.codedecode.demo.service.ProvinceDistrictService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.RankService;
import com.codedecode.demo.service.SalaryService;
import com.codedecode.demo.service.WorkExperienceService;
import com.codedecode.demo.service.WorkingFormService;
import com.codedecode.demo.service.YearOfExperienceService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/provincedistrict")
@Transactional
public class ProvinceDistrictController {

	@Autowired
	private ProvinceDistrictService provinceDistrictService;
	
	@Autowired
	private RankService rankService;
	
	@Autowired
	private WorkingFormService workingFormService;
	
	@Autowired
	private WorkExperienceService workExperienceService;

	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private PostingCategoryService postingCategoryService;
	
	@Autowired
	private YearOfExperienceService yearOfExperienceService;
	
	@Autowired
	private DesiredJobService desiredJobService;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/province")
	public ResponseEntity<DesiredJobRequestDTO> getAllProvince() {
		
		List<Rank> ranks = rankService.findAll();
		List<WorkingForm> workingForms = workingFormService.findAll();
		List<WorkExperiences> workExperiences = workExperienceService.findAll();
		List<YearOfExperience> yearOfExperiences = yearOfExperienceService.findAll();
		List<Salary> salaries = salaryService.findAll();
		List<Province> provinces = provinceService.findAll();
		List<PostingCategory> postingCategories = postingCategoryService.findAll();
		
		
		DesiredJobRequestDTO response = DesiredJobRequestDTO.builder()
				.ranks(ranks)
				.workingForms(workingForms)
				.workExperiences(workExperiences)
				.yearOfExperiences(yearOfExperiences)
				.salaries(salaries)
				.provinces(provinces)
				.postingCategories(postingCategories)
				.build();
		
		return new ResponseEntity<DesiredJobRequestDTO>(response, HttpStatus.OK);
	}
	
	@GetMapping("/district/{provinceId}")
	public ResponseEntity<?> getAllDistrict(@PathVariable Long provinceId) {
		List<City> list = provinceDistrictService.getAllDistrict(provinceId);
		return new ResponseEntity<List<City>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/update")
	public ResponseEntity<DesiredJob> updateDesiredJob(@RequestBody UpdateDesireJobRequestDTO request) {
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
		desiredJob = desiredJobService.update(desiredJob, rank, workingForm, yearOfExperience, salary, addresss, postingCategory);
		return  new ResponseEntity<DesiredJob>(desiredJob, HttpStatus.OK);
	}
}
