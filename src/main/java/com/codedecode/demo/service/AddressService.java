package com.codedecode.demo.service;

import java.util.HashSet;
import java.util.List;
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
		List<Address> address = addressRepository.findAddressByProvinceAndCity(provinceId, cityId);
		return address.get(0);
	}
	
	public Address findAddressById(Long addressId) {
		return addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFound(ExceptionMessage.ADDRESS_NOT_FOUND.getErrorMessage()));
	}
	
	public Set<Address> findAddressByPostingId(Long postingId) {
		return addressRepository.findByPostingId(postingId);
	}
	
	public Set<Address> findAddressByProvince(List<Long> provinceId){
		Set<Address> addressess = new HashSet<>();
		for (int i = 0; i < provinceId.size(); i++) {
			Address address = addressRepository.findByProvince_Id(provinceId.get(i)).iterator().next();
			addressess.add(address);
		}
		return addressess;
	}
}
