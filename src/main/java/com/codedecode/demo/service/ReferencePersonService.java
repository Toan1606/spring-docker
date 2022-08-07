package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.ReferencePersonRepository;

@Service
public class ReferencePersonService {

	@Autowired
	private ReferencePersonRepository referencePersonRepository;
	
	public User findReferencePersonById(Long id) {
		return referencePersonRepository.getReferencePersonById(id);
	}
	
	public void deleteReferencePersonById(Long id) {
		referencePersonRepository.deleteReferencePersonById(id);
	}
}
