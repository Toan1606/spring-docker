package com.codedecode.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.codedecode.demo.dto.ActivityUpdateRequestDTO;
import com.codedecode.demo.repository.ActivityRepository;
import com.codedecode.demo.service.ActivityService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ActivityServiceTest {

	@MockBean
	private ActivityRepository activityRepository;
	
	@InjectMocks
	private ActivityService activityService;
	
	@Test
	public void testUpdateActivity1() {
		List<ActivityUpdateRequestDTO> list = new ArrayList<ActivityUpdateRequestDTO>();
		Integer effectColumn = 1;
		
		ActivityUpdateRequestDTO activityDTO = new ActivityUpdateRequestDTO();
		
		activityDTO.setId(2L);
		activityDTO.setName("Mùa Hè Summer 2021");
		activityDTO.setPosition("Tester");
		activityDTO.setDescription("Làm Đồ Án Ra Trường");
		
		list.add(activityDTO);
		
		when(activityRepository.updateActivity(activityDTO.getId(), 
				activityDTO.getName(), activityDTO.getPosition(), 
				activityDTO.getDescription())).thenReturn(effectColumn);
		
		Integer reality = activityService.updateActivity(list);
		
		assertThat(reality).isEqualTo(1);
	}
	
	@Test
	public void testUpdateActivity2() {
		List<ActivityUpdateRequestDTO> list = new ArrayList<ActivityUpdateRequestDTO>();
		Integer effectColumn = 1;
		
		ActivityUpdateRequestDTO activityDTO = new ActivityUpdateRequestDTO();
		
		activityDTO.setId(1L);
		activityDTO.setName("Mùa Hè Summer 2021");
		activityDTO.setPosition("Tester");
		activityDTO.setDescription("Làm Đồ Án Ra Trường");
		
		list.add(activityDTO);
		
		when(activityRepository.updateActivity(activityDTO.getId(), 
				activityDTO.getName(), activityDTO.getPosition(), 
				activityDTO.getDescription())).thenReturn(effectColumn);
		
		Integer reality = activityService.updateActivity(list);
		
		assertThat(reality).isLessThan(1);
	}
	@Test
	public void testUpdateActivity3() {
		List<ActivityUpdateRequestDTO> list = new ArrayList<ActivityUpdateRequestDTO>();
		Integer effectColumn = 1;
		
		ActivityUpdateRequestDTO activityDTO = new ActivityUpdateRequestDTO();
		
		activityDTO.setId(1L);
		activityDTO.setName("Mùa Hè Summer 2021");
		activityDTO.setPosition("Tester");
		activityDTO.setDescription("Làm Đồ Án Ra Trường");
		
		list.add(activityDTO);
		
		when(activityRepository.updateActivity(activityDTO.getId(), 
				activityDTO.getName(), activityDTO.getPosition(), 
				activityDTO.getDescription())).thenReturn(effectColumn);
		
		Integer reality = activityService.updateActivity(list);
		
		assertThat(reality).isGreaterThan(1);
	}
}
