package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.CareerGoal;
import com.codedecode.demo.repository.CareerGoalRepository;

@Service
@Transactional
public class CareerGoalService {

	@Autowired
	private CareerGoalRepository careerGoalRepository;
	
	public CareerGoal getCareerGoalByUserId(Long userId) {
		return careerGoalRepository.getCareerGoalByUserId(userId);
	}
	public CareerGoal getCareerGoalById(Long id) {
		return careerGoalRepository.getCareerGoalById(id);
	}
	
	public void updateCareerGoal(CareerGoal careerGoal) {
		careerGoalRepository.save(careerGoal);
	}
	public void addCareerGoal(CareerGoal careerGoal) {
		careerGoalRepository.save(careerGoal);
	}
}
