package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.InvolvedProject;

@Repository
public interface InvolvedProjectRepository extends JpaRepository<InvolvedProject, Long>{

	@Modifying
	@Query(value = "UPDATE involved_projects SET description = :description, project_description = :projectDescription, project_name = :name WHERE id = :id", nativeQuery = true)
	Integer updateInvolvedProject(@Param("id") Long id, @Param("name") String name,  @Param("description") String description,  @Param("projectDescription") String projectDescription );
}
