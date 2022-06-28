package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.repository.SavedJobRepository;

@Service
public class SavedJobService {
	@Autowired
	private SavedJobRepository saveJobRepository;
	
	public List<SavedJob> getAllSavedJobs(Long studentId){
		return saveJobRepository.findAllSavedJobsByStudentId(studentId);
	}
	public void deleteSavedJobById(Long id) {
		saveJobRepository.deleteSavedJob(id);
	}
}
