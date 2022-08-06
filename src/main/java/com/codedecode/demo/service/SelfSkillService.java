package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.SelfSkill;
import com.codedecode.demo.repository.SelfSkillRepository;

@Service
@Transactional
public class SelfSkillService {

	@Autowired
	private SelfSkillRepository selfSkillRepository;
	
	public SelfSkill getSelfSkillByUserId(Long userId) {
		return selfSkillRepository.getSelfSkillByUserId(userId);
	}
	
	public SelfSkill getSelfSkillById(Long id) {
		return selfSkillRepository.getSelfSkillById(id);
	}
	
	public void updateSelfSkill(SelfSkill selfSkill) {
		selfSkillRepository.save(selfSkill);
	}
	
	public void addSelfSkill(SelfSkill selfSkill) {
		selfSkillRepository.save(selfSkill);
	}
}
