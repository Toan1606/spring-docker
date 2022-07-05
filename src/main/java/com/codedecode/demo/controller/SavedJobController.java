package com.codedecode.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Transactional
public class SavedJobController {
	
	@Autowired
	private SavedJobService savedJobService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showSavedJobsPage(@PathVariable Long userId){
		List<SavedJob> listSavedJob = savedJobService.getAllSavedJobs(userId);
		if(listSavedJob.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<SavedJob>>(listSavedJob,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSavedJob(@PathVariable Long id){
		savedJobService.deleteSavedJob(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
