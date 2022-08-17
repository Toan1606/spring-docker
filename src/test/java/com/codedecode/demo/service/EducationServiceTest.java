package com.codedecode.demo.service;

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

import com.codedecode.demo.dto.EducationUpdateRequestDTO;
import com.codedecode.demo.repository.EducationRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EducationServiceTest {

	@MockBean
	private EducationRepository educationRepository;

	@InjectMocks
	private EducationService educationService;

	@Test
	public void testUpdateEducation() {
		List<EducationUpdateRequestDTO> educations = new ArrayList<EducationUpdateRequestDTO>();
		Integer expect = 6;

		when(educationRepository.updateEducation(2L, "Giỏi", "Hệ Thống Thông Tin", "Trường ĐH Bách Khoa Hà Nội"))
				.thenReturn(expect);

		Integer reality = educationService.updateEducation(educations);
		assertNotNull(reality);
	}
	
	
}
