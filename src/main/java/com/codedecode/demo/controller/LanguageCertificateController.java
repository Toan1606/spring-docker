package com.codedecode.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.LanguageDTO;
import com.codedecode.demo.entity.Language;
import com.codedecode.demo.exception.CustomIllegalArgumentException;
import com.codedecode.demo.repository.LanguageCertificateRepository;

@RestController
@RequestMapping("/language")
public class LanguageCertificateController {
	
	@Autowired
	private LanguageCertificateRepository languageCertificateService;

	@GetMapping("/")
	public ResponseEntity<List<Language>> showLanguageCertitficates() {
		List<Language> list = languageCertificateService.findAllByUserID(1);
		if(list == null || list.size() == 0) {
//			return new ResponseEntity<IllegalArgumentException>(HttpStatus.BAD_REQUEST);
		}
//		LanguageDTO lanDTO = LanguageDTO.builder().lists(list).build();
		return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
	}
//	@PutMapping("/add")
//	public ResponseEntity<Language> addLanguageCertificate(){
//		LanguageDTO languageDTO = new LanguageDTO("Tiếng Anh", "Toeic", "200", 1);
//		
//	}
}
