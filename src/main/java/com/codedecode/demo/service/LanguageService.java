package com.codedecode.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@Service
@Transactional
public class LanguageService{
	@Autowired
	private LanguageCertificateRepository languageCertificateRepository;
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public List<Language> findAllByUserID(int userID){
		return languageCertificateRepository.findAllByUserID(userID);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public void deleteLanguage(int languageId) {
		languageCertificateRepository.deleteLanguageById(languageId);
	}
	public void updateLanguage(Language language) {
		languageCertificateRepository.flush();
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public Language findLanguageByLanguageId(int languageId) {
		return languageCertificateRepository.findLanguageByLanguageId(languageId);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public Language addLanguage(Language language) {
		return languageCertificateRepository.save(language);
	}
}
