package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;
import com.codedecode.demo.repository.AppliedJobRepository;

@Service
public class AppliedJobService {
	
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	
	
	public List<AppliedJob> getAllAppliedJobs(Long userId) {
		return appliedJobRepository.getAllAppliedJobs(userId);
	}
	public AppliedJob getAppliedJobById(AppliedJobKey key) {
		return appliedJobRepository.findByAppliedJobKey(key);
	}
	public void deleteAppliedJobByAppliedJobKey(AppliedJobKey key) {
		appliedJobRepository.deleteByAppliedJobKey(key);
	}
	
	public void deleteAppliedJob(AppliedJobKey key) {
		appliedJobRepository.deleteById(key);
	}
	
	public int countNumberOfAppliedJob() {
		return appliedJobRepository.countNumberOfAppliedJob();
	}
	
	public AppliedJob addAppliedJob(AppliedJob appliedJob) {
		AppliedJob returnObject = appliedJobRepository.save(appliedJob);
		return returnObject;
	}
	
	public List<AppliedJob> findAppliedJobByRecruiterId(Long recruiterId) {
		return appliedJobRepository.findByRecruiter_Id(recruiterId);
	}
	
}

