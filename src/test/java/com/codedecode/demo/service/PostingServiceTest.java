package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.PostingRepository;

public class PostingServiceTest {

	@Mock
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
