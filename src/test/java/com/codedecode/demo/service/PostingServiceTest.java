package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.PostingRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
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
