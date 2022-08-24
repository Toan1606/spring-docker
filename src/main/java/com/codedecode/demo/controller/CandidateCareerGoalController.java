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
import com.codedecode.demo.service.CVService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/career-goal")
public class CandidateCareerGoalController {

	private final CVService cvService;

	@Autowired
	public CandidateCareerGoalController(CVService cvService) {
		this.cvService = cvService;
	}


	@GetMapping("/{userId}")
	public ResponseEntity<?> showCareerGoal(@PathVariable Long userId) {
		CV cv = cvService.getCVsByUserId(userId);
		CareerGoalDTO careerGoalDTO = new CareerGoalDTO();
		careerGoalDTO.setCareerGoal(cv.getCareerJobObjective());
		careerGoalDTO.setUserId(userId);
		return new ResponseEntity<CareerGoalDTO>(careerGoalDTO, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<?> updateCareerGoal(@RequestBody CareerGoalDTO careerGoalDTO) {
		CV cv = cvService.getCVsByUserId(careerGoalDTO.getUserId());
		cv.setCareerJobObjective(careerGoalDTO.getCareerGoal());
		cvService.update(cv);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
