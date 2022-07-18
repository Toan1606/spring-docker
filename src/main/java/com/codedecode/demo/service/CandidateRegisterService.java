package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.repository.SalaryRepository;
import com.codedecode.demo.repository.UserRepository;
import com.codedecode.demo.repository.WorkingFormRepository;
import com.codedecode.demo.repository.YearOfExperienceRepository;

@Service
@Transactional
public class CandidateRegisterService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	YearOfExperienceRepository experienceRepository;
	
	@Autowired
	WorkingFormRepository formRepository;
	
	@Autowired
	SalaryRepository salaryRepository;
	
	public User addCandidate(User user) {
		return userRepository.save(user);
	}
	
	public List<YearOfExperience> getAllYearOfExperience(){
		return experienceRepository.findAll();
	}
	
	public List<WorkingForm> getAllWorkingForm(){
		return formRepository.findAll();
	}
	
	public List<Salary> getAllSalary(){
		return salaryRepository.getAllSalary();
	}

}
