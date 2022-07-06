package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.WorkExperiences;

public interface WorkExperienceRepository extends JpaRepository<WorkExperiences, Long>{
	
	@Query(value="select * from work_experiences where cv_id = ?1", nativeQuery=true)
	List<WorkExperiences> getAllWorkExpByCVId(Long cv_id);
	
	@Query(value="select * from work_experiences where id = ?1", nativeQuery=true)
	WorkExperiences getWorkExpById(Long id);
	
	@Query(value="delete from work_experiences where id = ?1", nativeQuery=true)
	@Modifying
	void deleteWorkExpById(Long id);
}
