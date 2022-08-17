package com.codedecode.demo.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.repository.EducationRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class HomeAddressServiceTest {

	@MockBean
	private EducationRepository educationRepository;

}
