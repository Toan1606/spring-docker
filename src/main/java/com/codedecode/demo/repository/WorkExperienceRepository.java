package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codedecode.demo.entity.WorkExperiences;

public interface WorkExperienceRepository extends JpaRepository<WorkExperiences, Long>{
	
//	@Query(value="select * from work_experiences where ")
}
