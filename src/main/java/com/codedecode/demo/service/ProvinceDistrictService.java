package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.repository.DistrictRepository;
import com.codedecode.demo.repository.ProvinceRepository;

@Service
public class ProvinceDistrictService {

	@Autowired
	private ProvinceRepository provinceRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

	public List<Province> getAllProvince() {
		return provinceRepository.findAll();
	}
	public List<City> getAllDistrict(Long provinceId) {
		return districtRepository.getAllDistrict(provinceId);
	}
}
