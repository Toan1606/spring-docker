package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.codedecode.demo.entity.Posting;

public interface PostingRepository extends PagingAndSortingRepository<Posting, Long>{

	
	@Query(value = "select * from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id \r\n"
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();
	
}
