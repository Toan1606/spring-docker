package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Posting;

@Repository
public interface PostingRepository extends SearchRepository<Posting, Long> {

	@Query(value = "select * from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id \r\n"
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();

	@Query(value = "select posting.id, commission, deadline_for_submission, images, job_name, address.name as address, salary.name as salary "
			+ "from jobez.posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id limit 10", nativeQuery = true)
	List<Posting> findPosting();
}
