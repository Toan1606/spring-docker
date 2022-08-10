package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.entity.key.SavedJobKey;
import com.codedecode.demo.repository.SavedJobRepository;

@Service
public class SavedJobService {
	@Autowired
	private SavedJobRepository savedJobRepository;
	
	public List<SavedJob> getAllSavedJobs(Long userId){
		return savedJobRepository.getAllSavedJobs(userId);
	}
	public void deleteSavedJob(SavedJobKey key) {
		savedJobRepository.deleteBySavedJobKey(key);
	}
	public SavedJob getSavedJobById(SavedJobKey key) {
		return savedJobRepository.findBySavedJobKey(key);
	}
	public SavedJob addNewSavedJob(SavedJob savedjob) {
		SavedJob result = savedJobRepository.save(savedjob);
		return result;
	}
}
