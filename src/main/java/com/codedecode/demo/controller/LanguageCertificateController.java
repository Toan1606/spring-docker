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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Language;
import com.codedecode.demo.service.LanguageService;
import com.codedecode.demo.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/language")
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
	@GetMapping("/")
	public ResponseEntity<List<Language>> showLanguageCertitficates() {
		List<Language> list = languageService.findAllByUserID(1);
		if (list == null || list.size() == 0) {
//			return new ResponseEntity<IllegalArgumentException>(HttpStatus.BAD_REQUEST);
		}
//		LanguageDTO lanDTO = LanguageDTO.builder().lists(list).build();
		return new ResponseEntity<List<Language>>(list, HttpStatus.OK);
	}

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */
	@PostMapping("/add")
	public ResponseEntity<Language> addLanguageCertificate(@RequestBody Language language) {
//		User user = userService.findUserById(1);
//		Language l = new Language(2L, "Tieng Anh", "Toeic", 700, user);
		languageService.addLanguage(language);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */
	@DeleteMapping("/delete/{languageId}")
	@Transactional
	public ResponseEntity<?> deleteLanguage(@PathVariable("languageId") Long languageId) {
//		Language language = languageService.findLanguageByLanguageId(languageId);
//		languageService.deleteLanguage(languageId);
		System.out.println(languageId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */
	@GetMapping("/edit/{languageId}")
	public ResponseEntity<Language> showEditLanguage(@PathVariable("languageId") int languageId) {
		Language language = languageService.findLanguageByLanguageId(languageId);
		return new ResponseEntity<Language>(language, HttpStatus.OK);
	}

	@PostMapping("/editlanguage/{languageId}")
	public ResponseEntity<?> editLanguage(@PathVariable("languageId") int languageId) {
		Language language = languageService.findLanguageByLanguageId(languageId);
		language.setGrade(600);
		languageService.updateLanguage(language);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTutorial(@PathVariable("id") long id, @RequestBody Language language) {
		return new ResponseEntity<Language>(language, HttpStatus.OK);
	}
}
