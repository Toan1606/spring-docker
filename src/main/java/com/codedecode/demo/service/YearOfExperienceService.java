package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.exception.YearOfExperienceException;
import com.codedecode.demo.repository.YearOfExperienceRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class YearOfExperienceService {

	private final YearOfExperienceRepository yearOfExperienceRepository;

	@Autowired
	public YearOfExperienceService(YearOfExperienceRepository yearOfExperienceRepository) {
		this.yearOfExperienceRepository = yearOfExperienceRepository;
	}

	public YearOfExperience findYearOfExperienceById(Long yearOfExperiencesId) {
		return yearOfExperienceRepository.findById(yearOfExperiencesId).orElseThrow(() -> new YearOfExperienceException(ExceptionMessage.SALARY_EXCEPTION.getErrorMessage()));
	}
	
	public List<YearOfExperience> findAll() {
		return yearOfExperienceRepository.findAll();
	}
}
