package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.AppliedJob;

public interface AppliedJobRepository extends JpaRepository<AppliedJob, Long>{
	
	@Query(value="select * from applied_jobs where user_id = ?1", nativeQuery=true)
	List<AppliedJob>findAllAppliedJobsByUserId(Long userId);
	
	@Modifying
	@Query(value="delete from applied_jobs where id = ?1", nativeQuery=true)
	void deleteAppliedJob(Long id);
}
