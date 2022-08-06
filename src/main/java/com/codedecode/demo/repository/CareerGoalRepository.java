package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.CareerGoal;

@Repository
public interface CareerGoalRepository extends JpaRepository<CareerGoal, Long>{
	
	@Query(value="select * from career_goal where user_id = ?1", nativeQuery=true)
	CareerGoal getCareerGoalByUserId(Long userID);
	
	@Query(value="select * from career_goal where id = ?1", nativeQuery=true)
	CareerGoal getCareerGoalById(Long id);
}
