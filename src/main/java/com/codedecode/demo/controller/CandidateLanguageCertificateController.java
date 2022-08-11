package com.codedecode.demo.controller;

import java.util.ArrayList;
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

import com.codedecode.demo.dto.LanguageDTO;
import com.codedecode.demo.entity.Language;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.LanguageCertificateService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/language")
@Transactional
public class CandidateLanguageCertificateController {

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
		}else {
			List<LanguageDTO> lists = new ArrayList<LanguageDTO>();
			for (Language language : list) {
				LanguageDTO l = new LanguageDTO(language.getId(),language.getCertificateName(), language.getName(), language.getGrade(), userId);
				lists.add(l);
			}
			return new ResponseEntity<List<LanguageDTO>>(lists, HttpStatus.OK);
		}		
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
	@PostMapping("/isDuplicate")
	public ResponseEntity<?> isDuplicateLanguage(@RequestBody LanguageDTO languageDTO){
		Language language = new Language();
		User user = userService.findUserById(languageDTO.getUserId());
		language.setCertificateName(languageDTO.getCertificate_name());
		language.setName(languageDTO.getName());
		language.setGrade(languageDTO.getMark());
		language.setUser(user);
		boolean isDuplicate = languageService.findLanguage(language);
		return new ResponseEntity<Boolean>(isDuplicate, HttpStatus.OK);
	}

	/*
	 * 
	 * @author: Nguyễn Văn Tuấn
	 * 
	 */
	@PostMapping("/add")
	public ResponseEntity<?> addLanguageCertificate(@RequestBody LanguageDTO languageDTO){
//		Language language = languageService.findLanguage(languageDTO);
		User user = userService.findUserById(languageDTO.getUserId());
		Language l = new Language();
		l.setName(languageDTO.getName());
		l.setCertificateName(languageDTO.getCertificate_name());
		l.setGrade(languageDTO.getMark());
		l.setUser(user);
		languageService.addLanguageCertificate(l);
		return new ResponseEntity<>(HttpStatus.OK);
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
	@PostMapping("/update")
	public ResponseEntity<?> updateLanguageCertificateById(@RequestBody LanguageDTO languageDTO){
		User user = userService.findUserById(languageDTO.getUserId());
		Language l = new Language();
		l.setName(languageDTO.getName());
		l.setCertificateName(languageDTO.getCertificate_name());
		l.setGrade(languageDTO.getMark());
		l.setUser(user);
		l.setId(languageDTO.getId());
		languageService.updateLanguageCertificate(l);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
