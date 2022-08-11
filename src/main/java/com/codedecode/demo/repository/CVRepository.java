package com.codedecode.demo.repository;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.CV;

@Repository
public interface CVRepository extends JpaRepository<CV, Long>{

	/*
	 * 
	 * @author: TuanNV
	 * 
	 * */
	@Query(value="select * from cvs where id = ?1", nativeQuery=true)
	CV getCVById(Long id);
	
	@Query(value="select * from cvs as c join users as u on c.id = u.cv_id where u.id = ?1", nativeQuery = true)
	CV getCVsByUserId(Long userId);
	
	CV findByUser_Id(Long id);

	@Modifying
	@Query(value = "UPDATE [cvs] SET [images] = :base64 WHERE id = 1", nativeQuery = true)
	Integer updateCv(@Param("base64") String base64);
}
