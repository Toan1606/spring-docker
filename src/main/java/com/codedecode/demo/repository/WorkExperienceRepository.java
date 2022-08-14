package com.codedecode.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codedecode.demo.entity.WorkExperiences;

public interface WorkExperienceRepository extends JpaRepository<WorkExperiences, Long> {

	@Query(value = "select * from work_experiences as w join cvs as c on w.cv_id = c.id join users as u on c.id = u.cv_id where u.id = ?1", nativeQuery = true)
	List<WorkExperiences> getAllWorkExpByUserId(Long userId);

	@Query(value = "select * from work_experiences where id = ?1", nativeQuery = true)
	WorkExperiences getWorkExpById(Long id);

	@Query(value = "delete from work_experiences where id = ?1", nativeQuery = true)
	@Modifying
	void deleteWorkExpById(Long id);

	@Query(value = "UPDATE work_experiences SET company_name = :companyName, start_date = :startDate, end_date = :endDate, position = :position where id = :id", nativeQuery = true)
	@Modifying
	Integer updateWorkExp(@Param("id") Long id, @Param("companyName") String companyName,
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("position") String position);
}
