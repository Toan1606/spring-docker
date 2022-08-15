package com.codedecode.demo.controller;

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

import com.codedecode.demo.dto.DesiredJobRequestDTO;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.service.PostingCategoryService;
import com.codedecode.demo.service.ProvinceDistrictService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.RankService;
import com.codedecode.demo.service.SalaryService;
import com.codedecode.demo.service.WorkExperienceService;
import com.codedecode.demo.service.WorkingFormService;

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
	
	@GetMapping("/province")
	public ResponseEntity<DesiredJobRequestDTO> getAllProvince() {
		
		List<Rank> ranks = rankService.findAll();
		List<WorkingForm> workingForms = workingFormService.findAll();
		List<WorkExperiences> workExperiences = workExperienceService.findAll();
		List<Salary> salaries = salaryService.findAll();
		List<Province> provinces = provinceService.findAllProvince();
		List<PostingCategory> postingCategories = postingCategoryService.findAll();
		
		DesiredJobRequestDTO response = DesiredJobRequestDTO.builder()
				.ranks(ranks)
				.workingForms(workingForms)
				.workExperiences(workExperiences)
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
}
