package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;

@Service
@Transactional
public class RecruiterRegisterService {
	
	@Autowired
	UserRepository repository;
	
	public User addRecruiter(User user) {
		return repository.save(user);
	}
	
//	public User updateRecruiter(Long id, User user, MultipartFile file) {
//		
//		return repository.updateRecruiterById(id);
//	}

}
