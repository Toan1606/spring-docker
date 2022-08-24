package com.codedecode.demo.controller;

import com.codedecode.demo.dto.*;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.Notification;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.key.AppliedJobKey;
import com.codedecode.demo.service.AppliedJobService;
import com.codedecode.demo.service.NotificationService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/applied-job")
public class AppliedJobController {

	private final AppliedJobService appliedJobService;

	private final UserService userService;

	private final PostingService postingService;

	private final NotificationService notificationService;
	@Autowired
	public AppliedJobController(AppliedJobService appliedJobService, UserService userService, PostingService postingService, NotificationService notificationService) {
		this.appliedJobService = appliedJobService;
		this.userService = userService;
		this.postingService = postingService;
		this.notificationService = notificationService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> showAppliedJobsPage(@PathVariable Long userId) {
		List<AppliedJob> listAppliedJob = appliedJobService.getAllAppliedJobs(userId);
		if (listAppliedJob.size() == 0) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		} else {
			List<AppliedJobDTO> listAppliedJobDTOs = new ArrayList<>();
			for (AppliedJob appliedJob : listAppliedJob) {
				Posting p = appliedJob.getPosting();
				AppliedJobDTO aDTO = new AppliedJobDTO();
				aDTO.setPositionJobname(p.getJobName());
				aDTO.setPostingPosition(p.getPosition());
				aDTO.setDateSubmission(appliedJob.getDateSubmission());
				aDTO.setDeadlineForSubmission(p.getDeadlineForSubmission());
				aDTO.setCommentFromEmployer(appliedJob.getCommentFromEmployer());
				aDTO.setPostingId(p.getId());
				aDTO.setRecruiterId(p.getUser().getId());
				aDTO.setUserId(userId);
				listAppliedJobDTOs.add(aDTO);
			}
			Comparator<AppliedJobDTO> reverseComparator = (c1, c2) -> { 
		        return c2.getDateSubmission().compareTo(c1.getDateSubmission()); 
			};
			Collections.sort(listAppliedJobDTOs, reverseComparator);
			return new ResponseEntity<List<AppliedJobDTO>>(listAppliedJobDTOs, HttpStatus.OK);
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<?> deleteAppliedJob(@RequestBody AppliedJobKeyDTO key) {
		AppliedJobKey appliedJobKey = new AppliedJobKey();
		appliedJobKey.setCandidateId(key.getCandidateId());
		appliedJobKey.setRecruiterId(key.getRecruiterId());
		appliedJobKey.setPostingId(key.getPostingId());
		
		AppliedJob a = appliedJobService.getAppliedJobById(appliedJobKey);
		if (a == null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
		} else {
			appliedJobService.deleteAppliedJob(appliedJobKey);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<AppliedJobDTOResponse> appliedNewJob(@RequestBody AppliedNewJobDTO request) {
		// 1. get request information
		String candidateEmail = request.getCandidateEmail();
		String recruiterEmail = request.getRecruiterEmail();
		Long postingId = request.getPostingId();
		
		// 2. query
		User candidate = userService.findUserByEmail(candidateEmail);
		User recruiter = userService.findUserByEmail(recruiterEmail);
		Posting posting = postingService.findPostingById(postingId);

		// 3. set object
		AppliedJob appliedJob = new AppliedJob();
		AppliedJobKey appliedJobKey = AppliedJobKey.builder().candidateId(candidate.getId())
				.recruiterId(recruiter.getId()).postingId(postingId).build();
		appliedJob.setAppliedJobKey(appliedJobKey);
		appliedJob.setPosting(posting);
		
		appliedJob.setCandidate(candidate);
		appliedJob.setRecruiter(recruiter);

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		appliedJob.setDeadlineForSubmission(date);
		appliedJob.setDateSubmission(date);
		
		// 4. save to db
		
		// 5. add notification
		Notification notification = new Notification();
		notification.setDate(new Date());
		notification.setContent(new StringBuilder().append(recruiter.getName()).append(" vừa nộp hồ sơ").toString());
		notification.setUser(recruiter);
		notificationService.save(notification);
		
		AppliedJob result = appliedJobService.addAppliedJob(appliedJob);
		// 5. set dto to return
		AppliedJobDTOResponse response = AppliedJobDTOResponse.builder()
				.deadlineForSubmission(dateFormat.format(result.getDeadlineForSubmission()))
				.dateSubmission(dateFormat.format(result.getDateSubmission()))
				.commentFromEmployer(result.getCommentFromEmployer()).build();

		return new ResponseEntity<AppliedJobDTOResponse>(response, HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody AppliedCandidateRequestDTO appliedCandidateRequestDTO){
		appliedJobService.updateStatus(appliedCandidateRequestDTO);
		return new ResponseEntity<AppliedCandidateRequestDTO>(HttpStatus.OK);
	}
}
