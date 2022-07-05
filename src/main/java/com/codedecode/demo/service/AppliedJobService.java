package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.repository.AppliedJobRepository;

@Service
public class AppliedJobService {
	
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	
	public List<AppliedJob> getAllAppliedJobs(Long userId) {
		return appliedJobRepository.getAllAppliedJobs(userId);
	}
	public AppliedJob getAppliedJobById(Long id) {
		return appliedJobRepository.getAppliedJobById(id);
	}
	public void deleteAppliedJob(Long id) {
		appliedJobRepository.deleteAppliedJob(id);
	}
}
