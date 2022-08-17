package com.codedecode.demo.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.codedecode.demo.entity.AppliedJob;
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
		Long userId = 1L;
		List<AppliedJob> expect = new ArrayList<AppliedJob>();
		
		when(appliedJobRepository.getAllAppliedJobs(userId)).thenReturn(expect);
		
		List<AppliedJob> reality = appliedJobService.getAllAppliedJobs(userId);
		assertNotNull(reality);
	}
}
