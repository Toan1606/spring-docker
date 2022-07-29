package com.codedecode.demo.repository;

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
}
