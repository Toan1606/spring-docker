package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codedecode.demo.entity.Language;


public interface LanguageCertificateRepository extends JpaRepository<Language, Long>{
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="select * from languages l where l.user_id = ?1", nativeQuery=true)
	List<Language> findAllByUserID(Long userID);
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="select * from languages where id = ?1", nativeQuery = true)
	Language findLanguageByLanguageId(Long languageId);

	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="delete from languages where id = ?1", nativeQuery=true)
	@Modifying
	void deleteLanguageById(int languageId);
}
