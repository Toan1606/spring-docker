package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Degree;
import com.codedecode.demo.entity.DesiredJob;

@Repository
public interface DesiredJobRepository  extends JpaRepository<DesiredJob, Long>{
		
	@Query(value="select * from desired_job where user_id = ?1", nativeQuery= true)
	DesiredJob findDesiredJobByUserId(Long userId);
	
}
