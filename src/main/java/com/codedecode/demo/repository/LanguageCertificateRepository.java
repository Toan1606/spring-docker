package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.Language;


public interface LanguageCertificateRepository extends JpaRepository<Language, Long>{
	
	@Query(value="select * from languages l where l.user_id = ?1", nativeQuery=true)
	List<Language> findAllByUserID(int userID);
	
}
