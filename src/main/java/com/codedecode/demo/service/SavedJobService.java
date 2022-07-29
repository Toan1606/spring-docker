package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.repository.SavedJobRepository;

@Service
public class SavedJobService {
	@Autowired
	private SavedJobRepository savedJobRepository;
	
	public List<SavedJob> getAllSavedJobs(Long userId){
		return savedJobRepository.getAllSavedJobs(userId);
	}
	public void deleteSavedJob(Long id) {
		savedJobRepository.deleteSavedJob(id);
	}
	public SavedJob getSavedJobById(Long id) {
		return savedJobRepository.getSavedJobById(id);
	}
}
