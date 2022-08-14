package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.entity.key.SavedJobKey;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long>{
	
	@Query(value="select * from saved_jobs where candidate_id = ?1", nativeQuery=true)
	List<SavedJob> getAllSavedJobs(Long userId);
	
	void deleteBySavedJobKey(SavedJobKey key);

	SavedJob findBySavedJobKey(SavedJobKey key);
}
