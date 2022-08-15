package com.codedecode.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Activity;

@Repository
@Transactional
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	@Modifying
	@Query(value = "UPDATE activities SET acitvity_name = :name, position = :position, description = :description WHERE id = :id", nativeQuery = true)
	Integer updateActivity(@Param("id") Long id, @Param("name") String name, @Param("position") String position,
			@Param("description") String description);
}
