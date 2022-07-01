package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;

@Service
public class CandidateRegisterService {
	
	@Autowired
	UserRepository repository;
	
	public User addCandidate(User user) {
		return repository.save(user);
	}

}
