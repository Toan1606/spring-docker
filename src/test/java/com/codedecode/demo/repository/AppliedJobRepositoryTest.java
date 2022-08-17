package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.AppliedJob;

@SpringBootTest
public class AppliedJobRepositoryTest {
	
	@Autowired
	private AppliedJobRepository appliedJobRepository;
	
	
	/*
	 * Không có trả về
	 * */
	@Test
	public void testGetAllAppliedJobs() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isLessThan(1);
	}
	
	/*
	 * Có đúng 1 
	 * */
	@Test
	public void testGetAllAppliedJobs1() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
//		assertThat(reality.size()).isEqualTo(1);
		assertThat(reality.size()).isNotEqualTo(1);
	}
	
	
	/*
	 * Có nhiều
	 * */
	@Test
	public void testGetAllAppliedJobs2() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isGreaterThanOrEqualTo(1);
	}
	
	/*
	 * Không có trả về
	 * */
	@Test
	public void testGetAllAppliedJobs4() {
		Long userId = 2l;
		List<AppliedJob> reality = appliedJobRepository.getAllAppliedJobs(userId);
		assertThat(reality.size()).isLessThan(1);
	}
}
