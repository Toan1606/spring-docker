package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Posting;

@Repository
public interface HomePostingRepository extends JpaRepository<Posting, Long>{
	
	@Query(value = "select posting.id, commission, deadline_for_submission, images, job_name, address.name as address, salary.name as salary "
			+ "from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id limit 10", nativeQuery = true)
	List<Posting> findPosting();
	
}
