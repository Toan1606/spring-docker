package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.codedecode.demo.entity.Language;

@Repository
public interface LanguageCertificateRepository extends JpaRepository<Language, Long>{
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="select * from languages l where l.user_id = ?1", nativeQuery=true)
	List<Language> findAllByUserID(int userID);
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="select * from languages where id = ?1", nativeQuery = true)
	Language findLanguageByLanguageId(int languageId);

	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="delete from language where id = ?1", nativeQuery=true)
	void deleteLanguageById(int languageId);
}
