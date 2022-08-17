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

import com.codedecode.demo.dto.SkillRequestDTO;
import com.codedecode.demo.repository.SkillRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)

public class SkillServiceTest {
	@MockBean
	private SkillRepository skillRepository;
	
	@InjectMocks
	private SkillService skillService;
	
	@Test
	public void testUpdateSkills1() {
		List<SkillRequestDTO> skills = new ArrayList<SkillRequestDTO>();
		
		SkillRequestDTO skillDTO = new SkillRequestDTO();
		skillDTO.setName("ABC");
		skillDTO.setId(1L);
		
		skills.add(skillDTO);
		
		Integer expect = 0;
		when(skillRepository.updateSkills(skillDTO.getId(), skillDTO.getName())).thenReturn(expect);
		
		Integer effectColumn = skillService.updateSkills(skills);
		
		assertThat(effectColumn).isGreaterThan(0);
	}
	
	@Test
	public void testUpdateSkills2() {
		List<SkillRequestDTO> skills = new ArrayList<SkillRequestDTO>();
		
		SkillRequestDTO skillDTO = new SkillRequestDTO();
		skillDTO.setName("ABC");
		skillDTO.setId(1L);
		
		skills.add(skillDTO);
		
		Integer expect = 0;
		when(skillRepository.updateSkills(skillDTO.getId(), skillDTO.getName())).thenReturn(expect);
		
		Integer effectColumn = skillService.updateSkills(skills);
		
		assertThat(effectColumn).isLessThan(0);
	}
	@Test
	public void testUpdateSkills3() {
		List<SkillRequestDTO> skills = new ArrayList<SkillRequestDTO>();
		
		SkillRequestDTO skillDTO = new SkillRequestDTO();
		skillDTO.setName("ABC");
		skillDTO.setId(1L);
		
		skills.add(skillDTO);
		
		Integer expect = 0;
		when(skillRepository.updateSkills(skillDTO.getId(), skillDTO.getName())).thenReturn(expect);
		
		Integer effectColumn = skillService.updateSkills(skills);
		
		assertThat(effectColumn).isEqualTo(1);
	}
}
