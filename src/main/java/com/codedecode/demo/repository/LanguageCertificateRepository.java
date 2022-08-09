package com.codedecode.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	List<Language> findAllLanguageCertificatesByUserId(Long userID);
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="select * from languages where id = ?1", nativeQuery = true)
	Language findLanguageCertificateById(Long languageId);

	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@Query(value="delete from languages where id = ?1", nativeQuery=true)
	@Modifying
	void deleteLanguageCertificateById(Long languageId);
	
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
//	Language findLanguage()
	
}
