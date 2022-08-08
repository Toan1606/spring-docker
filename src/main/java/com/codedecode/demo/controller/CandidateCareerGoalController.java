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

import com.codedecode.demo.dto.CareerGoalDTO;
import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.CVService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/careergoal")
@Transactional
public class CandidateCareerGoalController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showCareerGoal(@PathVariable Long userId) {
		User user = userService.findUserById(userId);
		CareerGoalDTO careerGoalDTO = new CareerGoalDTO();
		careerGoalDTO.setCareerGoal(user.getCareerGoals());
		careerGoalDTO.setUserId(userId);
		return new ResponseEntity<CareerGoalDTO>(careerGoalDTO, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateCareerGoal(@RequestBody CareerGoalDTO careerGoalDTO) {
		User user = userService.findUserById(careerGoalDTO.getUserId());
		user.setCareerGoals(careerGoalDTO.getCareerGoal());
		userService.addNewUser(user);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
