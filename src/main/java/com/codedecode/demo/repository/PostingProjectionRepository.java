package com.codedecode.demo.repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class PostingProjectionRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
}
