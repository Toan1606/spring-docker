package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

	@Modifying
	@Query(value = "UPDATE Skills SET name = :name WHERE id = :id", nativeQuery = true)
	Integer updateSkills(@Param("id") Long id, @Param("name") String skillName);
}
