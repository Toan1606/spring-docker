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

import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.service.AppliedJobService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/appliedjob")
public class AppliedJobController {
	
	@Autowired
	private AppliedJobService appliedJobService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showAppliedJobs(@PathVariable("userId") Long userId){
		List<AppliedJob> list = appliedJobService.getAllAppliedByUserId(userId);
		return new ResponseEntity<List<AppliedJob>>(list, HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	@Transactional
	public ResponseEntity<?> deleteSavedJob(@PathVariable("id") Long appliedJobId){
		appliedJobService.deleteAppliedJob(appliedJobId);
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
}
