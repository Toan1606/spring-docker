package com.codedecode.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.LanguageDTO;
import com.codedecode.demo.entity.Language;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@Service
@Transactional
public class LanguageCertificateService{
	@Autowired
	private LanguageCertificateRepository languageCertificateRepository;
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public List<Language> findAllLanguageCertificatesByUserId(Long userId){
		return languageCertificateRepository.findAllLanguageCertificatesByUserId(userId);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
//	public Language findLanguage(LanguageDTO languageDTO) {
//		return languageCertificateRepository.
//	}
	
	
	public void deleteLanguageCertificate(Long languageId) {
		languageCertificateRepository.deleteLanguageCertificateById(languageId);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public void updateLanguageCertificate(Language language) {
		languageCertificateRepository.save(language);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public Language findLanguageCertificateById(Long languageId) {
		return languageCertificateRepository.findLanguageCertificateById(languageId);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	public Language addLanguageCertificate(Language language) {
		return languageCertificateRepository.save(language);
	}
}
