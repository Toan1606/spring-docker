package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.exception.AddressNotFound;
import com.codedecode.demo.repository.AddressRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address findAddressByProvinceAndCity(String provinceName, String cityName) {
		Address address = addressRepository.findByProvinceAndCity(provinceName, cityName).orElseThrow(() -> new AddressNotFound(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage(), null));
		return address;
	}
}
