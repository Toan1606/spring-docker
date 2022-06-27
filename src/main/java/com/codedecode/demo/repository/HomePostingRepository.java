package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Posting;

@Repository
public interface HomePostingRepository extends JpaRepository<Posting, Long>{

	@Query(value = "select posting.id, commission, deadline_for_submission, images, job_name, address.name as address, salary.name as salary "
			+ "from jobez.posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id "
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();
	
}
