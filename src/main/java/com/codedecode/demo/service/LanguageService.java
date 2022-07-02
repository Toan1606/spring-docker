package com.codedecode.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@Service
public class LanguageService{
	@Autowired
	private LanguageCertificateRepository languageCertificateRepository;
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public List<Language> findAllByUserID(Long userId){
		return languageCertificateRepository.findAllByUserID(userId);
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
