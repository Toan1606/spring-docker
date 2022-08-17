package com.codedecode.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.User;

@SpringBootTest
public class ReferencePersonRepositoryTest {
	
	@Autowired
	private ReferencePersonRepository personRepository;
	
	@Test
	public void getReferencePersonById() {
		User reality = personRepository.getReferencePersonById(1L);
		assertTrue(reality != null);
	}
	
	@Test
	public void getReferencePersonById1() {
		User reality = personRepository.getReferencePersonById(1L);
		assertThat(reality != null);
	}
	
}
