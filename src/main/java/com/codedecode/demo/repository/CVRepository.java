package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.CV;

public interface CVRepository extends JpaRepository<CV, Long>{

	/*
	 * 
	 * @author: TuanNV
	 * 
	 * */
	@Query(value="select * from cvs where id = ?1", nativeQuery=true)
	CV getCVById(Long id);
	
	@Query(value="select * from cvs as c join users as u where c.user_id = u.id and u.id = ?1", nativeQuery = true)
	List<CV>getCVsByUserId(Long userId);
}
