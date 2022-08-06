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
import com.codedecode.demo.entity.CareerGoal;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.CareerGoalService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/careergoal")
@Transactional
public class CandidateCareerGoalController {

	@Autowired
	private CareerGoalService careerGoalService;

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showCareerGoal(@PathVariable Long userId) {
		CareerGoal c = careerGoalService.getCareerGoalByUserId(userId);
		if (c == null) {
			CareerGoalDTO careerGoal = new CareerGoalDTO();
			careerGoal.setUserId(userId);
			careerGoal.setCareerGoal("");
			return new ResponseEntity<CareerGoalDTO>(careerGoal, HttpStatus.OK);
		} else {
			CareerGoalDTO careerGoal = new CareerGoalDTO();
			careerGoal.setUserId(userId);
			careerGoal.setCareerGoal(c.getCareerGoal());
			careerGoal.setId(c.getId());
			return new ResponseEntity<CareerGoalDTO>(careerGoal, HttpStatus.OK);
		}
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateCareerGoal(@RequestBody CareerGoalDTO careerGoalDTO) {
		CareerGoal c = careerGoalService.getCareerGoalById(careerGoalDTO.getId());
		if (c != null) {
			User u = userService.findUserById(careerGoalDTO.getUserId());
			CareerGoal careerGoal = new CareerGoal();
			careerGoal.setUser(u);
			careerGoal.setCareerGoal(careerGoalDTO.getCareerGoal());
			careerGoal.setId(careerGoalDTO.getId());
			careerGoalService.updateCareerGoal(careerGoal);
		} else {
			User u = userService.findUserById(careerGoalDTO.getUserId());
			CareerGoal careerGoal = new CareerGoal();
			careerGoal.setUser(u);
			careerGoal.setCareerGoal("");
			careerGoalService.addCareerGoal(careerGoal);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
