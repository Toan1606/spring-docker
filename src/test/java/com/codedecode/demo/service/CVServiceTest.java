package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.entity.CV;
import com.codedecode.demo.repository.CVRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class CVServiceTest {
	@MockBean
	private CVRepository cvRepository;

	@InjectMocks
	private CVService cvService;

	@Test
	public void testGetCVById() {
		Long id = 1L;

		CV cv = new CV();

		when(cvRepository.getCVById(id)).thenReturn(cv);

		CV reality = cvService.getCVById(id);

		assertThat(reality).isNotNull();
	}

	@Test
	public void testGetCVsByUserId() {
		Long userId = 1L;

		CV cv = new CV();

		when(cvRepository.getCVsByUserId(userId)).thenReturn(cv);

		CV reality = cvService.getCVsByUserId(userId);

		assertThat(reality).isNotNull();
	}

	@Test
	public void testFindCvByCandidateId() {
		Long candidate_id = 1L;

		CV cv = new CV();

		when(cvRepository.findByUser_Id(candidate_id)).thenReturn(cv);

		CV reality = cvService.findCvByCandidateId(candidate_id);

		assertThat(reality).isNotNull();
	}

}
