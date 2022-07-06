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

import com.codedecode.demo.entity.CV;
import com.codedecode.demo.entity.WorkExperiences;
import com.codedecode.demo.service.CVService;
import com.codedecode.demo.service.WorkExperienceService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/workexp")
@Transactional
public class WorkExperienceController {

	@Autowired
	private WorkExperienceService workExperienceService;
	
	@Autowired
	private CVService cvService;
	
	@GetMapping("/{cv_id}")
	public ResponseEntity<?> showWorkExpPage(@PathVariable Long cv_id){
		List<WorkExperiences> list = workExperienceService.getAllWorkExp(cv_id);
		if(list == null || list.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<WorkExperiences>>(list,HttpStatus.OK);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteWorkExp(@PathVariable Long id){
		WorkExperiences w = workExperienceService.getWorkExpById(id);
		if(w == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}else {
			workExperienceService.deleteWorkExp(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?> addWorkExp(@RequestBody WorkExperiences workexp){
		CV cv = cvService.getCVById(1l);
		workexp.setCv(cv);
		workExperienceService.addWorkExp(workexp);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
