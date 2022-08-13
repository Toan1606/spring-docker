package com.codedecode.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.SavedJobDTO;
import com.codedecode.demo.dto.SavedJobKeyDTO;
import com.codedecode.demo.dto.SavedNewJobDTO;
import com.codedecode.demo.dto.SavedNewJobResponseDTO;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.SavedJob;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.key.SavedJobKey;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.SavedJobService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/savedjob")
@Transactional
public class CandidateSavedJobController {

	@Autowired
	private SavedJobService savedJobService;

	@Autowired
	private UserService userService;

	@Autowired
	private PostingService postingService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> showSavedJobsPage(@PathVariable Long userId) {
		List<SavedJob> listSavedJob = savedJobService.getAllSavedJobs(userId);
		if (listSavedJob.size() == 0) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		} else {
			List<SavedJobDTO> listSavedJobRequestDTOs = new ArrayList<>();
			for (SavedJob s : listSavedJob) {
				SavedJobDTO sDTO = new SavedJobDTO();
				Posting p = s.getPosting();
				sDTO.setPostingJobname(p.getJobName());
				sDTO.setDeadlineForSubmission(p.getDeadlineForSubmission());
				sDTO.setPostingPosition(p.getPosition());
				sDTO.setPostingId(p.getId());
				sDTO.setUserId(userId);
				listSavedJobRequestDTOs.add(sDTO);
			}
			return new ResponseEntity<List<SavedJobDTO>>(listSavedJobRequestDTOs, HttpStatus.OK);
		}
	}
	@PostMapping("/remove")
	public ResponseEntity<?> deleteSavedJob(@RequestBody SavedJobKeyDTO savedJobKeyDTO){
		SavedJobKey savedJobKey = new SavedJobKey();
		savedJobKey.setUserId(savedJobKeyDTO.getUserId());
		savedJobKey.setPostingId(savedJobKeyDTO.getPostingId());
		
		SavedJob savejob = savedJobService.getSavedJobById(savedJobKey);
		if(savejob == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		}else {
			savedJobService.deleteSavedJob(savedJobKey);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<SavedNewJobResponseDTO> saveJob(@RequestBody SavedNewJobDTO request) {
		String email = request.getEmail();
		Long postingId = request.getPostingId();

		User user = userService.findUserByEmail(email);

		Posting posting = postingService.findPostingById(postingId);

		SavedJob savedjob = new SavedJob();
		
		SavedJobKey savedJobKey = new SavedJobKey(user.getId(), postingId);

		Date date = new Date();

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		savedjob.setPosting(posting);

		savedjob.setUser(user);

		savedjob.setSavedDate(date);

		savedjob.setSavedJobKey(savedJobKey);

		savedJobService.addNewSavedJob(savedjob);


		// dto to return
		SavedNewJobResponseDTO response = SavedNewJobResponseDTO.builder()
				.deadlineForSubmission(dateFormat.format(date))
				.build();

		return new ResponseEntity<SavedNewJobResponseDTO>(response, HttpStatus.OK);
	}
}
