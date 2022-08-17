package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.codedecode.demo.dto.WorkExperienceDTO;
import com.codedecode.demo.dto.WorkExperienceUpdateRequestDTO;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.repository.WorkExperienceRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class WorkExperienceServiceTest {
	@MockBean
	private WorkExperienceRepository workExperienceRepository;
	
	@InjectMocks
	private WorkExperienceService workExperienceService;
	
	@Test
	public void testGetAllWorkExp1() {
		Long userId =  1L;
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.getAllWorkExpByUserId(userId)).thenReturn(list);
		List<WorkExperiences> reality = workExperienceService.getAllWorkExp(userId);
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	@Test
	public void testGetAllWorkExp2() {
		Long userId =  1L;
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.getAllWorkExpByUserId(userId)).thenReturn(list);
		List<WorkExperiences> reality = workExperienceService.getAllWorkExp(userId);
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testGetAllWorkExp3() {
		Long userId =  1L;
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.getAllWorkExpByUserId(userId)).thenReturn(list);
		List<WorkExperiences> reality = workExperienceService.getAllWorkExp(userId);
		
		assertThat(reality.size()).isEqualTo(0);
	}
	
	@Test
	public void testGetWorkExpById1() {
		Long id =  1L;
		WorkExperiences workexp = new WorkExperiences();
		
		when(workExperienceRepository.getWorkExpById(id)).thenReturn(workexp);
		
		WorkExperiences reality = workExperienceService.getWorkExpById(id);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testAddWorkExp() {
		WorkExperiences workexp = new WorkExperiences();
		
		when(workExperienceRepository.save(workexp)).thenReturn(workexp);
		
		WorkExperiences reality = workExperienceService.addWorkExp(workexp);
		
		assertThat(reality).isNotNull();
	}
	
	@Test
	public void testFindAll() {
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.findAll()).thenReturn(list);
		
		List<WorkExperiences> reality = workExperienceService.findAll();
		
		assertThat(reality.size()).isGreaterThan(0);
	}
	
	@Test
	public void testFindAll1() {
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.findAll()).thenReturn(list);
		
		List<WorkExperiences> reality = workExperienceService.findAll();
		
		assertThat(reality.size()).isLessThan(0);
	}
	@Test
	public void testFindAll2() {
		List<WorkExperiences> list = new ArrayList<WorkExperiences>();
		
		when(workExperienceRepository.findAll()).thenReturn(list);
		
		List<WorkExperiences> reality = workExperienceService.findAll();
		
		assertThat(reality.size()).isEqualTo(0);
	}
	@Test
	public void testUpdateWorkExp1() {
		Integer effectCol = 0;
		WorkExperienceUpdateRequestDTO workexp = new WorkExperienceUpdateRequestDTO();
		List<WorkExperienceUpdateRequestDTO> list = new ArrayList<WorkExperienceUpdateRequestDTO>();
		
		workexp.setId(2L);
		workexp.setCompanyName("GPT");
		workexp.setStartDate(new Date());
		workexp.setEndDate(new Date());
		workexp.setPosition("Truong phong");
		
		list.add(workexp);
		
		when(workExperienceRepository.updateWorkExp(workexp.getId(), workexp.getCompanyName(), workexp.getStartDate(), workexp.getEndDate(), workexp.getPosition())).thenReturn(effectCol);
		
		Integer reality = workExperienceService.updateWorkExperiences(list);
		
		assertThat(reality).isEqualTo(1);
	}
	@Test
	public void testUpdateWorkExp2() {
		Integer effectCol = 0;
		WorkExperienceUpdateRequestDTO workexp = new WorkExperienceUpdateRequestDTO();
		List<WorkExperienceUpdateRequestDTO> list = new ArrayList<WorkExperienceUpdateRequestDTO>();
		
		workexp.setId(2L);
		workexp.setCompanyName("GPT");
		workexp.setStartDate(new Date());
		workexp.setEndDate(new Date());
		workexp.setPosition("Truong phong");
		
		list.add(workexp);
		
		when(workExperienceRepository.updateWorkExp(workexp.getId(), workexp.getCompanyName(), workexp.getStartDate(), workexp.getEndDate(), workexp.getPosition())).thenReturn(effectCol);
		
		Integer reality = workExperienceService.updateWorkExperiences(list);
		
		assertThat(reality).isEqualTo(0);
	}
}
