package com.codedecode.demo.service;

import java.util.Set;

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
	
	public Address findAddressByProvinceAndCity(Long provinceId, Long cityId) {
		System.out.println("findAddressByProvinceAndCity service");
		Address address = addressRepository.findAddressByProvinceAndCity(provinceId, cityId).orElseThrow(() -> new AddressNotFound(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage()));
		return address;
	}
	
	public Address findAddressById(Long addressId) {
		return addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFound(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage()));
	}
	
	public Set<Address> findAddressByPostingId(Long postingId) {
		return addressRepository.findByPostingId(postingId);
	}
}
