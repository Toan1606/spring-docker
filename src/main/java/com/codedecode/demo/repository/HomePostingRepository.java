package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Posting;

@Repository
public interface HomePostingRepository extends JpaRepository<Posting, Long>{
	
	@Query(value = "select id, commission, deadline_for_submission, images, job_name from posting", nativeQuery = true)
	List<Posting> findPosting();
	
}
