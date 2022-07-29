package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.entity.Posting;

@Repository
public interface PostingRepository extends SearchRepository<Posting, Long> {

	@Query(value = "select * from posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id \r\n"
			+ "join year_of_experience on posting.year_of_experience_id = year_of_experience.id", nativeQuery = true)
	List<Posting> getAllJob();

	@Query(value = "select posting.id, commission, deadline_for_submission, images, job_name, address.name as address, salary.name as salary "
			+ "from jobez.posting join address on posting.address_id = address.id join salary on posting.salary_id = salary.id limit 10", nativeQuery = true)
	List<Posting> findPosting();
	
	@Query(value = "select p.benefits, p.commission, p.deadline_for_submission as deadlineForSubmission, p.degree_required as degreeRequired, p.descriptions as description, p.email_contact as emailContact, p.files as file, p.gender_requirement as genderRequirement, p.job_requirement, p.phone_number, p.position, p.profile_included, p.quantity, p.quantity_needed, p.views, p.address_id, p.rank_id, p.salary_id, p.working_form_id, p.year_of_experience_id from jobez.posting p join users u on p.user_id = u.id and p.id = :postingId and u.id = :userId;", nativeQuery = true)
	PostingResponseInterfaceDTO findPostingByUserIdAndPostingId(@Param("userId") Long userId, @Param("postingId") Long postingId);
}
