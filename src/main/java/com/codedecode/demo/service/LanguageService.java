package com.codedecode.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@Service
public class LanguageService{
	@Autowired
	private LanguageCertificateRepository languageCertificateService;
	
	public List<Language> findAllByUserID(int userID){
		return languageCertificateService.findAllByUserID(userID);
	}
}
