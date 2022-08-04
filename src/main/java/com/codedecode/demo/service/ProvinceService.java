package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
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
	
	public List<Map<String, String>> findByAddress(Set<Address> addresss) {
		List<Map<String, String>> provinces = new ArrayList<Map<String,String>>();
		
		for (Address address : addresss) {
			Map<String, String> provincesMap = new HashMap<String, String>();
			Province province = provinceRepository.findByAddressId(address.getId());
			if (province != null) {
				provincesMap.put("id", String.valueOf(province.getId()));
				provincesMap.put("name", province.getName());
				provinces.add(provincesMap);
			}
		}
		return provinces;
	}
	
	public Province findByAddressId(Long addressId) {
		return provinceRepository.findByAddressId(addressId);
	}
}
