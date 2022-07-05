package com.codedecode.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.LanguageService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/language")
@Transactional
public class LanguageCertificateController {

	@Autowired
	private LanguageService languageService;

	@Autowired
	private UserService userService;

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */

	@GetMapping("/user/{userId}")
	public ResponseEntity<?> showAllLanguageCertitficates(@PathVariable Long userId) {
		List<Language> list = languageService.findAllByUserID(userId);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

		}
//		LanguageDTO lanDTO = LanguageDTO.builder().lists(list).build();
		return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
	}

	@GetMapping("/editlanguage/{id}")
	public ResponseEntity<?> getLanguageById(@PathVariable Long id){
		Language language = languageService.findLanguageByLanguageId(id);
		if(language != null) {
			return new ResponseEntity<Language>(language, HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addLanguageCertificate(@RequestBody Language language){
		User user = userService.findUserById(1);
//		Language l = new Language(2L, "Tieng Anh", "Toeic", 700, user);
		Language l = languageService.findLanguageByLanguageId(language.getId());
		if(l == null) {
			language.setUser(user);
			languageService.addLanguage(language);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@DeleteMapping("/delete/{languageId}")
	public ResponseEntity<?> deleteLanguage(@PathVariable("languageId") Long languageId){
//		Language language = languageService.findLanguageByLanguageId(languageId);
		languageService.deleteLanguage(languageId);
		System.out.println(languageId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateLanguageById(@PathVariable("id") Long id,  @RequestBody Language language){
		System.out.println(language.toString());
		Language l = languageService.findLanguageByLanguageId(id);
		if(l != null) {
			l.setCertificateName(language.getCertificateName());
			l.setGrade(language.getGrade());
			l.setName(language.getName());
			languageService.updateLanguage(l);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}

	}
}
