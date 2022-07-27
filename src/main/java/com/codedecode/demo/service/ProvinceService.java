package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Province;
import com.codedecode.demo.exception.ProvinceNotFound;
import com.codedecode.demo.repository.ProvinceRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	public Province findProvinceById(Long id) {
		return provinceRepository.findById(id).orElseThrow(() -> new ProvinceNotFound(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage()));
	}
	
	public Province findProvinceByName(String provinceName) {
		Province province = provinceRepository.findByName(provinceName).orElseThrow(() -> new ProvinceNotFound(ExceptionMessage.PROVINCE_NOT_FOUND.getErrorMessage()));
		return province;
	}
	
	public List<Province> findAllProvince() {
		return provinceRepository.findAll();
	}
}
