package com.codedecode.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@Service
@Transactional
public class LanguageCertificateService{

	private final LanguageCertificateRepository languageCertificateRepository;

	@Autowired
	public LanguageCertificateService(LanguageCertificateRepository languageCertificateRepository) {
		this.languageCertificateRepository = languageCertificateRepository;
	}

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
	public boolean findLanguage(Language language) {
		List<Language> list = languageCertificateRepository.findAllLanguageCertificatesByUserId(language.getUser().getId());
		for (Language l : list) {
			if(l.getCertificateName().equalsIgnoreCase(language.getCertificateName()) 
					&& l.getName().equalsIgnoreCase(language.getName()) 
					&& l.getGrade() == language.getGrade()
					&& l.getUser().equals(language.getUser())) {
				return true;
			}
		}
		return false;
	}
	
	
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
