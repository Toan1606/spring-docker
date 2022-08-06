package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.SelfSkillDTO;
import com.codedecode.demo.entity.SelfSkill;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.SelfSkillService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/selfskill")
@Transactional
public class CandidateSelfSkillController {

	@Autowired
	private SelfSkillService selfSkillService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showSelfSkill(@PathVariable Long userId){
		SelfSkill s = selfSkillService.getSelfSkillByUserId(userId);
		if (s == null) {
			SelfSkillDTO selfSkill = new SelfSkillDTO();
			selfSkill.setUserId(userId);
			selfSkill.setSelfSkill("");
			return new ResponseEntity<SelfSkillDTO>(selfSkill, HttpStatus.OK);
		} else {
			SelfSkillDTO selfSkill = new SelfSkillDTO();
			selfSkill.setUserId(userId);
			selfSkill.setSelfSkill(s.getSelfSkill());
			selfSkill.setId(s.getId());
			return new ResponseEntity<SelfSkillDTO>(selfSkill, HttpStatus.OK);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateCareerGoal(@RequestBody SelfSkillDTO selfSkillDTO) {
		SelfSkill s = selfSkillService.getSelfSkillById(selfSkillDTO.getId());
		if (s != null) {
			User u = userService.findUserById(selfSkillDTO.getUserId());
			SelfSkill selfSkill = new SelfSkill();
			selfSkill.setUser(u);
			selfSkill.setSelfSkill(selfSkillDTO.getSelfSkill());
			selfSkill.setId(selfSkillDTO.getId());
			selfSkillService.updateSelfSkill(selfSkill);
		} else {
			User u = userService.findUserById(selfSkillDTO.getUserId());
			SelfSkill selfSkill = new SelfSkill();
			selfSkill.setUser(u);
			selfSkill.setSelfSkill(selfSkillDTO.getSelfSkill());
			selfSkillService.addSelfSkill(selfSkill);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
