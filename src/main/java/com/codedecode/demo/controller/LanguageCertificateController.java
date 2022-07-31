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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.LanguageCertificateService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/language")
@Transactional
public class LanguageCertificateController {

	@Autowired
	private LanguageCertificateService languageService;

	@Autowired
	private UserService userService;

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */

	@GetMapping("/{userId}")
	public ResponseEntity<?> showAllLanguageCertitficates(@PathVariable Long userId) {
		List<Language> list = languageService.findAllLanguageCertificatesByUserId(userId);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);

		}
//		LanguageDTO lanDTO = LanguageDTO.builder().lists(list).build();
		return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
	}

	@GetMapping("/editlanguage/{id}")
	public ResponseEntity<?> getLanguageCertificateById(@PathVariable Long id){
		Language language = languageService.findLanguageCertificateById(id);
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
		Language l = languageService.findLanguageCertificateById(language.getId());
		if(l == null) {
			language.setUser(user);
			languageService.addLanguageCertificate(language);
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
	public ResponseEntity<?> deleteLanguageCertificate(@PathVariable("languageId") Long languageId){
		Language language = languageService.findLanguageCertificateById(languageId);
		if(language == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}else {
			languageService.deleteLanguageCertificate(languageId);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}
	/*
	 * 
	 *	@author: Nguyễn Văn Tuấn 
	 * 
	 */
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateLanguageCertificateById(@PathVariable("id") Long id,  @RequestBody Language language){
//		System.out.println(language.toString());
		Language l = languageService.findLanguageCertificateById(id);
		if(l != null) {
			l.setCertificateName(language.getCertificateName());
			l.setGrade(language.getGrade());
			l.setName(language.getName());
			languageService.updateLanguageCertificate(l);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}else {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}

	}
}
