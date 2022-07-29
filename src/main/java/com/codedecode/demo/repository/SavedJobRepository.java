package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.SavedJob;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long>{
	
	@Query(value="select * from saved_jobs where student_id = ?1", nativeQuery=true)
	List<SavedJob> getAllSavedJobs(Long userId);
	
	@Query(value="delete from saved_jobs where id = ?1", nativeQuery=true)
	@Modifying
	void deleteSavedJob(Long savedJobId);
	
	@Query(value="select * from saved_jobs where id = ?1", nativeQuery=true)
	SavedJob getSavedJobById(Long id);
}
