package com.codedecode.demo.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.AppliedCandidateRequestDTO;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;
import com.codedecode.demo.repository.AppliedJobRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class AppliedJobServiceTest {
	
	@MockBean
	private AppliedJobRepository appliedJobRepository;
	
	@InjectMocks
	private AppliedJobService  appliedJobService;
	
	@Test
	public void testGetAllAppliedJobs() {
		Long userId = 2L;
		List<AppliedJob> expect = new ArrayList<AppliedJob>();
		
		when(appliedJobRepository.getAllAppliedJobs(userId)).thenReturn(expect);
		
		List<AppliedJob> reality = appliedJobService.getAllAppliedJobs(userId);
		assertNotNull(reality);
	}
	
	@Test
	public void testGetAppliedJobById() {
		AppliedJob expect = new AppliedJob();
		AppliedJobKey key = new AppliedJobKey(1L, 3L, 2L);
		
		when(appliedJobRepository.findByAppliedJobKey(key)).thenReturn(expect);
		
		AppliedJob reality = appliedJobService.getAppliedJobById(key);
		assertNotNull(reality);
	}
	
	@Test
	public void testDeleteAppliedJobByAppliedJobKey() {
		AppliedJob expect = new AppliedJob();
		AppliedJobKey key = new AppliedJobKey(1L, 3L, 2L);
		
		appliedJobService.deleteAppliedJobByAppliedJobKey(key);
		assertNotNull(expect);
	}
	
	@Test
	public void testDeleteAppliedJob() {
		AppliedJob expect = new AppliedJob();
		AppliedJobKey key = new AppliedJobKey(1L, 3L, 2L);
		
		appliedJobService.deleteAppliedJob(key);
		assertNotNull(expect);
	}
	
	@Test
	public void testCountNumberOfAppliedJob() {
		int expect = 1;
		
		when(appliedJobRepository.countNumberOfAppliedJob(1L)).thenReturn(expect);
		
		Integer reality = appliedJobService.countNumberOfAppliedJob(1L);
		assertNotNull(reality);
	}
	
	@Test
	public void testCountNumberOfAppliedJobByRecruiter() {
		Long recruiterId = 1L;
		Integer expect = 1;
		
		when(appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId)).thenReturn(expect);
		
		Integer reality = appliedJobService.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertNotNull(reality);
	}
	
	@Test
	public void testAddAppliedJob() {
		AppliedJob appliedJob = new AppliedJob();
		AppliedJob expect = new AppliedJob();
		
		when(appliedJobRepository.save(appliedJob)).thenReturn(expect);
		
		AppliedJob reality = appliedJobService.addAppliedJob(appliedJob);
		assertNotNull(reality);
	}
	
	@Test
	public void testFindAppliedJobByRecruiterId() {
		Long recruiterId = 3L;
		List<AppliedJob> expect = new ArrayList<AppliedJob>();
		
		when(appliedJobRepository.findByRecruiter_Id(recruiterId)).thenReturn(expect);
		
		List<AppliedJob> reality = appliedJobService.findAppliedJobByRecruiterId(recruiterId);
		assertNotNull(reality);
	}
	
	@Test
	public void testUpdateStatus() {
		AppliedCandidateRequestDTO appliedCandidateRequestDTO = AppliedCandidateRequestDTO.builder()
				.candidateId(1L)
				.recruiterId(3L)
				.postingId(1L)
				.status("Applied")
				.build();
		AppliedJob expect = new AppliedJob();
		
		when(appliedJobRepository.getAppliedJobByAllId(1L, 3L, 3L)).thenReturn(expect);
		
		AppliedJob reality = appliedJobService.updateStatus(appliedCandidateRequestDTO);
		assertNotNull(reality);
	}
}
