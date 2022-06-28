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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.service.SavedJobService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/savedjob")
public class SavedJobController {

	@Autowired
	private SavedJobService savedJobService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showSavedJobs(@PathVariable("userId") Long userId){
		List<SavedJob> list = savedJobService.getAllSavedJobs(userId);
		return new ResponseEntity<List<SavedJob>>(list, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> deleteSavedJob(@PathVariable("id") Long savedJobId){
		savedJobService.deleteSavedJobById(savedJobId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
