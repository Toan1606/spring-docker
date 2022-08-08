package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.AppliedJob;

@Repository
public interface AppliedJobRepostory extends JpaRepository<AppliedJob, Long>{
	
	@Query(value = "SELECT COUNT(*) AS NumberOfAppliedJob FROM applied_jobs", nativeQuery = true) 
	int countNumberOfAppliedJob();
}
