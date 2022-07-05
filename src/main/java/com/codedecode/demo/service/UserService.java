package com.codedecode.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User addNewUser(User user) {
		return userRepository.save(user);
	}
	public User findUserById(int id) {
		return userRepository.getUserById(id);
	}
	
	public void updateCandidateOnlineCVForm() {
		userRepository.flush();
	}
}
