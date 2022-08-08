package com.codedecode.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.User;

public interface ReferencePersonRepository extends JpaRepository<User, Long>{
	
	@Query(value="select * from Users where id = ?1", nativeQuery=true)
	User getReferencePersonById(Long id);
	
	@Query(value="delete from Users where id = ?1", nativeQuery=true)
	void deleteReferencePersonById(Long id);
}
