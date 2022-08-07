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
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/selfskill")
@Transactional
public class CandidateSelfSkillController {
	
	@Autowired
	private UserService userService;
//	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showSelfSkill(@PathVariable Long userId){
		User user = userService.findUserById(userId);
		SelfSkillDTO selfSkillDTO = new SelfSkillDTO();
		selfSkillDTO.setSelfSkill(user.getSelfSkill());
		selfSkillDTO.setUserId(userId);
		return new ResponseEntity<SelfSkillDTO>(selfSkillDTO, HttpStatus.OK);
	}
//	
	@PostMapping("/update")
	public ResponseEntity<?> updateCareerGoal(@RequestBody SelfSkillDTO selfSkillDTO) {
		User user = userService.findUserById(selfSkillDTO.getUserId());
		user.setSelfSkill(selfSkillDTO.getSelfSkill());
		userService.addNewUser(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
