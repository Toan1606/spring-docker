package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.codedecode.demo.entity.Posting;

public interface HomePostingRepository extends PagingAndSortingRepository<Posting, Long>{

	
	@Query(value = "select posting.id, benefits, commission, deadline_for_submission, degree_required, description, gender_requirement, images, "
			+ "job_name, job_requirement, position, probationary_period, profile_included,quantity, quantity_needed, address.name as address, "
			+ "salary.name as salary from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id "
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();
	
}
