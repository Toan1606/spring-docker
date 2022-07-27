package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.exception.StreetNotFound;
import com.codedecode.demo.repository.StreetRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class StreetService {
	
	@Autowired
	private StreetRepository streetRepository;
	
	public Street findStreetById(Long id) {
		return streetRepository.findById(id).orElseThrow(() -> new StreetNotFound(ExceptionMessage.STREET_NOT_FOUND.getErrorMessage()));
	}
	
	public List<Street> findStreetByCityId(City city) {
		return streetRepository.findByCity(city);
	}
}
