package com.codedecode.demo.repository;




import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Repository;

import com.codedecode.demo.dto.PostingResponseDTO;

@Repository
public class PostingProjectionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public PostingResponseDTO findPostingByUserIdAndPostingId(Long userId, Long postingId) {
		TypedQuery<PostingResponseDTO> tq = entityManager.createQuery("SELECT new com.codedecode.demo.dto.PostingResponseDTO(p.benefits, p.commission, p.deadlineForSubmission, p.degreeRequired, p.description, p.emailContact, p.file, p.genderRequirement, p.images, p.jobName, p.jobRequirement, p.phoneNumber, p.position, p.profileIncluded, p.quantity, p.quantityNeeded, p.view, p.address.id, p.rank.id, p.salary.id, p.workingForm.id, p.yearOfExperience.id, u.id, u.name, pc.id, pc.categoryName) from User u join Posting p on u.id = p.user.id and u.id = :userId and p.id = :postingId join PostingCategory pc on p.postingCategory.id = pc.id", PostingResponseDTO.class);
		tq.setParameter("userId", userId);
		tq.setParameter("postingId", postingId);
		PostingResponseDTO posting = tq.getSingleResult();
		return posting;
	}
	
	public PostingResponseDTO findPostingByUserIdAndPostingIdResultTransformer(Long userId, Long postingId) {
		return PostingResponseDTO.builder().build();
	}

	
}
