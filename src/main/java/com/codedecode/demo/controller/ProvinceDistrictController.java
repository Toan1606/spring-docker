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
import com.codedecode.demo.entity.YearOfExperience;
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
@RequestMapping("api/v1/province-district")
public class ProvinceDistrictController {

	private final ProvinceDistrictService provinceDistrictService;

	private final RankService rankService;

	private final WorkingFormService workingFormService;

	private final WorkExperienceService workExperienceService;

	private final SalaryService salaryService;

	private final ProvinceService provinceService;

	private final PostingCategoryService postingCategoryService;

	private final YearOfExperienceService yearOfExperienceService;

	@Autowired
	public ProvinceDistrictController(ProvinceDistrictService provinceDistrictService, RankService rankService, WorkingFormService workingFormService,
									  WorkExperienceService workExperienceService, SalaryService salaryService, ProvinceService provinceService,
									  PostingCategoryService postingCategoryService, YearOfExperienceService yearOfExperienceService) {
		this.provinceDistrictService = provinceDistrictService;
		this.rankService = rankService;
		this.workingFormService = workingFormService;
		this.workExperienceService = workExperienceService;
		this.salaryService = salaryService;
		this.provinceService = provinceService;
		this.postingCategoryService = postingCategoryService;
		this.yearOfExperienceService = yearOfExperienceService;
	}
	
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
	
}
