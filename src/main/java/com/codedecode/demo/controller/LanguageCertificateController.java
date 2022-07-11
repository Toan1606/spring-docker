package com.codedecode.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.LanguageService;
import com.codedecode.demo.service.UserService;

@RestController
@RequestMapping("/language")
@CrossOrigin(origins = "http://localhost:8080")
public class LanguageCertificateController {
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private UserService userService;

	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@GetMapping("/")
	public ResponseEntity<List<Language>> showLanguageCertitficates() {
		List<Language> list = languageService.findAllByUserID(1);
		if(list == null || list.size() == 0) {
//			return new ResponseEntity<IllegalArgumentException>(HttpStatus.BAD_REQUEST);
		}
//		LanguageDTO lanDTO = LanguageDTO.builder().lists(list).build();
		return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@PutMapping("/add")
	public ResponseEntity<Language> addLanguageCertificate(){
		User user = userService.findUserById(1);
		Language l = new Language(2L, "Tieng Anh", "Toeic", 700, user);
		languageService.addLanguage(l);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@PostMapping("/delete/{languageId}")
	public ResponseEntity<?> deleteLanguage(@PathVariable("languageId") int languageId){
//		Language language = languageService.findLanguageByLanguageId(languageId);
		languageService.deleteLanguage(languageId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
