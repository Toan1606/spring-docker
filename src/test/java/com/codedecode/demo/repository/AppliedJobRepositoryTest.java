package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;

@SpringBootTest
public class AppliedJobRepositoryTest {

	@Autowired
	private AppliedJobRepository appliedJobRepository;

	/*
	 * Không có trả về
	 */
	@Test
	public void testGetAllAppliedJobs() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isLessThan(1);
	}

	/*
	 * Có đúng 1
	 */
	@Test
	public void testGetAllAppliedJobs1() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
//		assertThat(reality.size()).isEqualTo(1);
		assertThat(reality.size()).isNotEqualTo(1);
	}

	/*
	 * Có nhiều
	 */
	@Test
	public void testGetAllAppliedJobs2() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isGreaterThanOrEqualTo(1);
	}

	/*
	 * Không có trả về
	 */
	@Test
	public void testGetAllAppliedJobs4() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isLessThan(1);
	}

	@Test
	public void testFindByAppliedJobKey() {
		AppliedJobKey AppliedJobKey = new AppliedJobKey(1L, 1L, 1L);
		AppliedJob appliedJob = appliedJobRepository.findByAppliedJobKey(AppliedJobKey);
		assertNotNull(appliedJob);
	}

	@Test
	public void testFindByAppliedJobKey2() {
		AppliedJobKey AppliedJobKey = new AppliedJobKey(1L, 1L, 1L);
		AppliedJob appliedJob = appliedJobRepository.findByAppliedJobKey(AppliedJobKey);
		assertNull(appliedJob);
	}

	@Test
	public void testFindByAppliedJobKey3() {
		AppliedJobKey AppliedJobKey = new AppliedJobKey(1L, 1L, 1L);
		AppliedJob appliedJob = appliedJobRepository.findByAppliedJobKey(AppliedJobKey);
		assertNull(appliedJob.getCommentFromEmployer());
	}

	@Test
	public void testFindByAppliedJobKey4() {
		AppliedJobKey AppliedJobKey = new AppliedJobKey(1L, 1L, 1L);
		AppliedJob appliedJob = appliedJobRepository.findByAppliedJobKey(AppliedJobKey);
		assertNotNull(appliedJob.getCommentFromEmployer());
	}

	@Test
	public void testCountNumberOfAppliedJob() {
		Integer numberOfAppliedJob = appliedJobRepository.countNumberOfAppliedJob(1L);
		assertThat(numberOfAppliedJob).isGreaterThan(0);
	}

	@Test
	public void testCountNumberOfAppliedJob2() {
		Integer numberOfAppliedJob = appliedJobRepository.countNumberOfAppliedJob(1L);
		assertThat(numberOfAppliedJob).isEqualTo(0);
	}

	@Test
	public void testCountNumberOfAppliedJob3() {
		Integer numberOfAppliedJob = appliedJobRepository.countNumberOfAppliedJob(1L);
		assertThat(numberOfAppliedJob).isLessThan(0);
	}

	@Test
	public void testCountNumberOfAppliedJob4() {
		Integer numberOfAppliedJob = appliedJobRepository.countNumberOfAppliedJob(1L);
		assertNull(numberOfAppliedJob);
	}

	@Test
	public void testCountNumberOfAppliedJob5() {
		Integer numberOfAppliedJob = appliedJobRepository.countNumberOfAppliedJob(1L);
		assertNotNull(numberOfAppliedJob);
	}

	@Test
	public void testCountNumberOfAppliedJobByRecruiter() {
		Long recruiterId = 3L;
		Integer appliedJob = appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertNull(appliedJob);
	}
	
	@Test
	public void testCountNumberOfAppliedJobByRecruiter2() {
		Long recruiterId = 3L;
		Integer appliedJob = appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertNotNull(appliedJob);
	}
	
	@Test
	public void testCountNumberOfAppliedJobByRecruiter3() {
		Long recruiterId = 3L;
		Integer appliedJob = appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertThat(appliedJob).isGreaterThan(0);
	}
	
	@Test
	public void testCountNumberOfAppliedJobByRecruiter4() {
		Long recruiterId = 3L;
		Integer appliedJob = appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertThat(appliedJob).isEqualTo(0);
	}
	
	@Test
	public void testCountNumberOfAppliedJobByRecruiter5() {
		Long recruiterId = 3L;
		Integer appliedJob = appliedJobRepository.countNumberOfAppliedJobByRecruiter(recruiterId);
		assertThat(appliedJob).isLessThan(0);
	}
	
	@Test
	public void testFindByRecruiter_Id() {
		Long recruiterId = 3L; 
		List<AppliedJob> appliedJob = appliedJobRepository.findByRecruiter_Id(recruiterId);
		assertNull(appliedJob);
	}
	
	@Test
	public void testFindByRecruiter_Id2() {
		Long recruiterId = 3L; 
		List<AppliedJob> appliedJob = appliedJobRepository.findByRecruiter_Id(recruiterId);
		assertNotNull(appliedJob);
	}
	
	@Test
	public void testGetPostingById() {
		Long postingId = 1L;
		List<AppliedJob>  reality = appliedJobRepository.getPostingById(postingId);
		assertNull(reality);
	}
	
	@Test
	public void testGetPostingById2() {
		Long postingId = 1L;
		List<AppliedJob>  reality = appliedJobRepository.getPostingById(postingId);
		assertNotNull(reality);
	}
	
	@Test
	public void testGetAppliedJobByAllId() {
		AppliedJob  reality = appliedJobRepository.getAppliedJobByAllId(1L, 1L, 3L);
		assertNotNull(reality);
	}
	
	@Test
	public void testGetAppliedJobByAllId2() {
		AppliedJob  reality = appliedJobRepository.getAppliedJobByAllId(1L, 1L, 3L);
		assertNull(reality);
	}
	
	@Test
	public void testUpdateStatus() {
		AppliedJob  reality = appliedJobRepository.updateStatus("Status", 1L, 3L, 3L);
		assertNull(reality);
	}
	
	@Test
	public void testUpdateStatus2() {
		AppliedJob  reality = appliedJobRepository.updateStatus("Status", 1L, 3L, 3L);
		assertNotNull(reality);
	}
}
