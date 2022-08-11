package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, AppliedJobKey>{

	@Query(value="select * from applied_jobs where user_id = ?1", nativeQuery=true)
	List<AppliedJob> getAllAppliedJobs(Long userId);
	
	@Query(value="select * from applied_jobs where id = ?1", nativeQuery = true)
	AppliedJob getAppliedJobById(Long id);
	
	@Query(value="delete from applied_jobs where id = ?1", nativeQuery=true)
	@Modifying
	void deleteAppliedJob(Long appliedJobId);
	
	@Query(value = "SELECT COUNT(*) AS NumberOfAppliedJob FROM applied_jobs", nativeQuery = true) 
	int countNumberOfAppliedJob();
	
	List<AppliedJob> findByRecruiter_Id(Long id);
	
	@Query(value="select * from applied_jobs where posting_id = ?1", nativeQuery=true)
	List<AppliedJob> getPostingById(Long id);
	
	@Query(value="delete from applied_jobs where posting_id = ?1", nativeQuery=true)
	@Modifying
	void deleteAppliedJobByPostingId(Long id);
	
//	void deleteByIdIn(List<Long> ids);
}
