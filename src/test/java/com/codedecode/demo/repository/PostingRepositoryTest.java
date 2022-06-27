package com.codedecode.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class PostingRepositoryTest {
	
	@Autowired
	private PostingRepository postingRepository;
	
	@Test
	public void testGetAllJob() {
		assertNotNull(postingRepository);
		assertEquals(postingRepository, postingRepository);
	}
}
