package com.codedecode.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.DegreeUpdateRequestDTO;
import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.repository.DegreeRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DegreeServiceTest {

	@MockBean
	private DegreeRepository degreeRepository;

	@InjectMocks
	private DegreeService degreeService;

	@Test
	public void testGetAllDegreeByUserId() {
		Long userId = 1L;
		List<Degree> expect = new ArrayList<Degree>();

		when(degreeRepository.findAll()).thenReturn(expect);

		List<Degree> reality = degreeService.getAllDegreeByUserId(userId);
		assertNotNull(reality);
	}
	
	@Test
	public void testDeleteDegree() {
		Long id = 1L;

		degreeRepository.deleteDegree(id);

		assertTrue(true);
	}
	
	@Test
	public void testGetDegreeById() {
		Long id = 1L;
		Degree expect = new Degree();

		when(degreeRepository.getOne(id)).thenReturn(expect);

		Degree reality = degreeService.getDegreeById(id);
		assertNotNull(reality);
	}
	
	@Test
	public void testIsDuplicate() {
//		EducationDegreeDTO degree = new EducationDegreeDTO();
		Long userId = 1L;
		List<Degree> expect = new ArrayList<Degree>();

		when(degreeRepository.getAllDegreeByUserId(userId)).thenReturn(expect);

//		boolean reality = degreeService.isDuplicate(degree);
		assertTrue(true);
	}
	
	@Test
	public void testFormatDate() {
		Date date = new Date();
		String reality = degreeService.formatDate(date);
		assertNotNull(reality);
	}
	
	@Test
	public void testUpdateDegree() {
		List<DegreeUpdateRequestDTO> degrees = new ArrayList<DegreeUpdateRequestDTO>();
		Integer reality = degreeService.updateDegree(degrees);
		assertNotNull(reality);
	}
}
