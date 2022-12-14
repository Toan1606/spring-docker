package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.exception.StreetNotFound;
import com.codedecode.demo.repository.StreetRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class StreetService {

	private final StreetRepository streetRepository;

	@Autowired
	public StreetService(StreetRepository streetRepository) {
		this.streetRepository = streetRepository;
	}

	public Street findStreetById(Long id) {
		return streetRepository.findById(id).orElseThrow(() -> new StreetNotFound(ExceptionMessage.STREET_NOT_FOUND.getErrorMessage()));
	}
	
	public List<Street> findStreetByCityId(City city) {
		return streetRepository.findByCity(city);
	}
	
	public List<String> findByAddress(Set<Address> addresss) {
		List<String> streets = new ArrayList<String>();
		for (Address address : addresss) {
			Street street = streetRepository.findByAddressId(address.getId());
			if (street != null) {
				streets.add(street.getName());
			}
		}
		
		return streets;
	}
	
	public Street findByAddressId(Long addressId) {
		return streetRepository.findByAddressId(addressId);
	}
}
