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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.AppliedJobDTO;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.service.AppliedJobService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/appliedjob")
@Transactional
public class CandidateAppliedJobController {

	@Autowired
	private AppliedJobService appliedJobService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showAppliedJobsPage(@PathVariable Long userId) {
		List<AppliedJob> listAppliedJob = appliedJobService.getAllAppliedJobs(userId);
		if (listAppliedJob.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}else {
			List<AppliedJobDTO> listAppliedJobDTOs = new ArrayList<>();
			for (AppliedJob appliedJob : listAppliedJob) {
				Posting p = appliedJob.getPosting();
				AppliedJobDTO aDTO = new AppliedJobDTO(); 
				aDTO.setId(appliedJob.getId());
				aDTO.setPositionJobname(p.getJobName());
				aDTO.setPostingPosition(p.getPosition());
				aDTO.setDateSubmission(appliedJob.getDateSubmission());
				aDTO.setDeadlineForSubmission(p.getDeadlineForSubmission());
				aDTO.setCommentFromEmployer(appliedJob.getCommentFromEmployer());
				listAppliedJobDTOs.add(aDTO);
			}
			return new ResponseEntity<List<AppliedJobDTO>>(listAppliedJobDTOs, HttpStatus.OK);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAppliedJob(@PathVariable Long id) {
		AppliedJob a = appliedJobService.getAppliedJobById(id);
		if (a == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		} else {
			appliedJobService.deleteAppliedJob(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}
}
