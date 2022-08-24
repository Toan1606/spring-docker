package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.AppliedCandidateRequestDTO;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;
import com.codedecode.demo.repository.AppliedJobRepository;

@Service
public class AppliedJobService {

	private final AppliedJobRepository appliedJobRepository;

	@Autowired
	public AppliedJobService( AppliedJobRepository appliedJobRepository) {
		this.appliedJobRepository = appliedJobRepository;
	}

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
	
	public int countNumberOfAppliedJob(Long id) {
		return appliedJobRepository.countNumberOfAppliedJob(id);
	}
	
	public int countNumberOfAppliedJobByRecruiter(Long recruiterId) {
		return appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
	}
	
	public AppliedJob addAppliedJob(AppliedJob appliedJob) {
		AppliedJob returnObject = appliedJobRepository.save(appliedJob);
		return returnObject;
	}
	
	public List<AppliedJob> findAppliedJobByRecruiterId(Long recruiterId) {
		return appliedJobRepository.findByRecruiter_Id(recruiterId);
	}
	
	public AppliedJob updateStatus(AppliedCandidateRequestDTO appliedCandidateRequestDTO) {
		Long candidateId = appliedCandidateRequestDTO.getCandidateId();
		Long postingId = appliedCandidateRequestDTO.getPostingId();
		Long recruiterId = appliedCandidateRequestDTO.getRecruiterId();
		String status = appliedCandidateRequestDTO.getStatus();
		AppliedJob appliedJob = appliedJobRepository.getAppliedJobByAllId(candidateId, postingId, recruiterId);
		appliedJob.setCommentFromEmployer(status);
		
		return appliedJob;
		
	}
}

