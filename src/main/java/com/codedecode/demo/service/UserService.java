package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired(required = true)
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
	
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
}
