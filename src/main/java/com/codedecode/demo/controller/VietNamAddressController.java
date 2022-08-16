package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.CityService;
import com.codedecode.demo.service.ProvinceService;

@RestController
@RequestMapping("/vietnam")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class VietNamAddressController {
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CityService cityService;
	

	@GetMapping(value = "/province")
	public ResponseEntity<List<Province>> getProvince() {
		return new ResponseEntity<List<Province>>(provinceService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/province/district/{provinceId}")
	public ResponseEntity<List<City>> getDistrictByProvince(@PathVariable("provinceId") Long provinceId) {
		return new ResponseEntity<List<City>>(cityService.findAllCityByProvinceId(provinceId), HttpStatus.OK);
	}
	
}
