package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.dto.SkillRequestDTO;
import com.codedecode.demo.exception.NotUpdateException;
import com.codedecode.demo.repository.SkillRepository;

@Service
@Transactional
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public Integer updateSkills(List<SkillRequestDTO> skills) {
		if (skills == null)
			return -1;

		int effectColumns = 0;
		for (SkillRequestDTO skill : skills) {
			int effect = skillRepository.updateSkills(skill.getId(), skill.getName());
			System.out.println("id : " + skill.getId());
			System.out.println("name : " + skill.getName());
			effectColumns += effect;
		}

		if (effectColumns != skills.size()) {
			throw new NotUpdateException("Skill Is Not Update");
		}
		return effectColumns;
	}
}
