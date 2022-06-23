package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.PostingRepository;

public class PostingServiceTest {

	@MockBean
	private PostingRepository postingRepository;
	
	@InjectMocks
	private PostingService postingService;
	
	
	@Test
	public void testGetUrgentJob() {
		when(postingRepository.getAllJob()).thenReturn(null);
		Iterable<Posting> urgentJobs = postingService.getUrgentJob();
		assertEquals(urgentJobs, null);
	}
}
