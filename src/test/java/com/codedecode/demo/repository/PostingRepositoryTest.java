package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.codedecode.demo.dto.PostingResponseDTO;

@DataJpaTest
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
	
	@Test
	public void testPostingProjectionJPQL() {
		PostingResponseDTO posting = postingProjectionRepository.findPostingByUserIdAndPostingId(1L, 1L);
		System.out.println("posting : " + posting);
		assertNotNull(posting);
	}
}
