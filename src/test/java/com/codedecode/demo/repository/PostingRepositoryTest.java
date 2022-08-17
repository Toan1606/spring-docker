package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostingRepositoryTest {
	
	@Mock
	private PostingRepository postingRepository;
	
	@Mock
	private PostingProjectionRepository postingProjectionRepository;
	
	@Test
	public void testGetAllJob() {
		assertNotNull(postingRepository);
		assertEquals(postingRepository, postingRepository);
	}
}
