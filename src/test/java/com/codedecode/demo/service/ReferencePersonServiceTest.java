package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.User;
import com.codedecode.demo.repository.ReferencePersonRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ReferencePersonServiceTest {

	@MockBean
	private ReferencePersonRepository referencePersonRepository;
	
	@InjectMocks
	private ReferencePersonService referencePersonService;
	
	@Test
	public void testFindReferencePersonById() {
		Long id = 1L;
		User expect = new User();
		
		when(referencePersonRepository.getReferencePersonById(id)).thenReturn(expect);
		
		User reality = referencePersonService.findReferencePersonById(id);
		assertNotNull(reality);
	}
	
	@Test
	public void testDeleteReferencePersonById() {
		Long id = 1L;
		User expect = new User();
		
		when(referencePersonRepository.getReferencePersonById(id)).thenReturn(expect);
		
		User reality = referencePersonService.findReferencePersonById(id);
		assertNotNull(reality);
	}
}
