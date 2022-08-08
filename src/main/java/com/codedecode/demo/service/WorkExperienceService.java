package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.repository.WorkExperienceRepository;

@Service
public class WorkExperienceService {
	@Autowired
	private WorkExperienceRepository workExperienceRepository;
	
	public List<WorkExperiences> getAllWorkExp(Long userId){
		return workExperienceRepository.getAllWorkExpByUserId(userId);
	}
	public WorkExperiences getWorkExpById(Long id) {
		return workExperienceRepository.getWorkExpById(id);
	}
	
	public WorkExperiences addWorkExp(WorkExperiences workexp) {
		return workExperienceRepository.save(workexp);
	}
	public void deleteWorkExp(Long id) {
		workExperienceRepository.deleteWorkExpById(id);
	}
}
