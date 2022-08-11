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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.service.ProvinceDistrictService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/provincedistrict")
@Transactional
public class ProvinceDistrictController {

	@Autowired
	private ProvinceDistrictService provinceDistrictService;

	@GetMapping("/province")
	public ResponseEntity<?> getAllProvince() {
		List<Province> list = provinceDistrictService.getAllProvince();
		return new ResponseEntity<List<Province>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/district/{provinceId}")
	public ResponseEntity<?> getAllDistrict(@PathVariable Long provinceId) {
		List<City> list = provinceDistrictService.getAllDistrict(provinceId);
		return new ResponseEntity<List<City>>(list, HttpStatus.OK);
	}
}
