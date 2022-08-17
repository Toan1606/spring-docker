package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.key.AppliedJobKey;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, AppliedJobKey>{

	@Query(value="select * from applied_jobs where candidate_id = ?1", nativeQuery=true)
	List<AppliedJob> getAllAppliedJobs(Long userId);
	
	AppliedJob findByAppliedJobKey(AppliedJobKey appliedJobKey);
	
	void deleteByAppliedJobKey(AppliedJobKey key);
	
	@Query(value = "SELECT COUNT(*) AS NumberOfAppliedJob FROM applied_jobs", nativeQuery = true) 
	int countNumberOfAppliedJob();
	
	@Query(value = "SELECT COUNT(*) AS NumberOfAppliedJob FROM applied_jobs aj where aj.recruiter_id = :recruiter_id", nativeQuery = true) 
	int countNumberOfAppliedJobByRecruiter(@Param("recruiter_id") Long recruiterId);
	
	List<AppliedJob> findByRecruiter_Id(Long id);
	
	@Query(value="select * from applied_jobs where posting_id = ?1", nativeQuery=true)
	List<AppliedJob> getPostingById(Long id);
	
	@Query(value="delete from applied_jobs where posting_id = ?1", nativeQuery=true)
	@Modifying
	void deleteAppliedJobByPostingId(Long id);
	
	@Query(value="select * from applied_jobs where candidate_id = ?1 and posting_id = ?2 and recruiter_id = ?3", nativeQuery=true)
	AppliedJob getAppliedJobByAllId(Long candidateId, Long postingId, Long recruiterId);
	
	@Query(value="update applied_jobs set comment_from_employer = ?1 where candidate_id = ?2 and posting_id = ?3 and recruiter_id = ?4", nativeQuery=true)
	AppliedJob updateStatus(String status, Long candidateId, Long postingId, Long recruiterId);
//	void deleteByIdIn(List<Long> ids);
}
