package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long> {

	@Modifying
	@Query(value = "UPDATE educations SET classification = :classification, major = :major, university_name = :universityName WHERE id = :id", nativeQuery = true)
	Integer updateEducation(@Param("id") Long id, @Param("classification") String classification,
			@Param("major") String major, @Param("universityName") String universityName);
}
