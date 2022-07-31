package com.codedecode.demo.controller;

import java.util.ArrayList;
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

import com.codedecode.demo.dto.SavedJobDTO;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.service.SavedJobService;

@CrossOrigin(origins = "http://localhost:8080")
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
		}else {
			List<SavedJobDTO> listSavedJobRequestDTOs = new ArrayList<>();
			for(SavedJob s : listSavedJob) {
				SavedJobDTO sDTO = new SavedJobDTO();
				Posting p = s.getPosting();
				sDTO.setId(s.getId());
				sDTO.setPostingJobname(p.getJobName());
				sDTO.setDeadlineForSubmission(p.getDeadlineForSubmission());
				sDTO.setPostingPosition(p.getPosition());
				listSavedJobRequestDTOs.add(sDTO);
			}
			return new ResponseEntity<List<SavedJobDTO>>(listSavedJobRequestDTOs,HttpStatus.OK);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteSavedJob(@PathVariable Long id){
		SavedJob savejob = savedJobService.getSavedJobById(id);
		if(savejob == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}else {
			savedJobService.deleteSavedJob(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}
}
