package com.codedecode.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.AppliedCandidateDTO;
import com.codedecode.demo.dto.FindAllUserResponseDTO;
import com.codedecode.demo.dto.PostingRecruiterResponseDTO;
import com.codedecode.demo.dto.RecruiterAppliedJobRequestDTO;
import com.codedecode.demo.dto.RecruiterAppliedJobResponseDTO;
import com.codedecode.demo.dto.RecruiterDeleteAppliedJobRequestDTO;
import com.codedecode.demo.dto.RecruiterMangementResponseDTO;
import com.codedecode.demo.dto.RecrutierManagementRequestDTO;
import com.codedecode.demo.dto.RegisterRequestDTO;
import com.codedecode.demo.dto.UserRequestIdDTO;
import com.codedecode.demo.dto.UserResponseIdDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.key.AppliedJobKey;
import com.codedecode.demo.service.AppliedJobService;
import com.codedecode.demo.service.AuthService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.UserService;
import com.codedecode.demo.utils.ResponseMessage;

@RestController
@RequestMapping("/recruiter")
@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
public class RecruiterLoginController {

	@Autowired
	private AuthService authService;

	@Autowired
	private UserService userService;

	@Autowired
	private AppliedJobService appliedJobService;

	@Autowired
	private PostingService postingService;

	@PostMapping
	public ResponseEntity<UserResponseIdDTO> findUserById(@RequestBody UserRequestIdDTO request) {
		Long userId = request.getUserId();
		User user = userService.findUserById(userId);
		List<Posting> postings = new ArrayList<Posting>(user.getPostings());

		List<PostingRecruiterResponseDTO> postingsDto = userService.convert(postings);
		System.out.println("Length : " + postingsDto.size());
		Address address = user.getAddress();
		Province province = address.getProvince();
		City city = address.getCity();
		Street street = address.getStreet();

		UserResponseIdDTO response = UserResponseIdDTO.builder().recruiterId(user.getId()).recruiterName(user.getName())
				.description(user.getDescription()).city(city.getName()).province(province.getName())
				.street(street.getName()).recruiterDescription(user.getRecruiterDescription().substring(0, 50))
				.taxNumber(user.getTaxtNumber()).postings(postingsDto).build();

		return new ResponseEntity<UserResponseIdDTO>(response, HttpStatus.OK);
	}

	/*
	 * 
	 * @author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/register")
	public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));
	}

	/*
	 * 
	 * @author: Nguyen The Toan
	 * 
	 */
	@PostMapping(value = "/find-all")
	public ResponseEntity<List<FindAllUserResponseDTO>> findAllRecruiter() {
		List<User> users = userService.findAllRecruiter();
		List<FindAllUserResponseDTO> usersDto = userService.convertFindAllUser(users);
		return ResponseEntity.status(HttpStatus.CREATED).body(usersDto);
	}

	@PostMapping(value = "/recruiter-management")
	public ResponseEntity<RecruiterMangementResponseDTO> findRecuiterManagement(
			@RequestBody RecrutierManagementRequestDTO request) {
		Long recruiterId = request.getUserId();

		// 1. number of candidate who applied job
		int numberOfApplieds = appliedJobService.countNumberOfAppliedJob();

		// 2. number of posting which is posted by recruiter
		int numberOfPostings = postingService.countNumberOfPostingsByRecruiter(recruiterId);

		// 3. list of candidate who applied job's recruiter
		List<AppliedJob> appliedJobs = appliedJobService.findAppliedJobByRecruiterId(recruiterId);

		// 4. get applied candidate information
		List<AppliedCandidateDTO> candidates = new ArrayList<AppliedCandidateDTO>();
		// format date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		for (AppliedJob appliedJob : appliedJobs) {
			User candidate = appliedJob.getCandidate();
			Date dateSubmission = appliedJob.getDateSubmission();

			AppliedCandidateDTO appliedCandidate = AppliedCandidateDTO.builder().candidateId(candidate.getId())
					.candidateName(candidate.getName())
					.university(candidate.getUniversity())
					.dateSubmission(dateFormat.format(dateSubmission))
					.build();
			candidates.add(appliedCandidate);
		}

		// 4. list of lastest posting
		List<Posting> postings = postingService.findLastestPostingByRecruiterId(recruiterId);

		// 5. dto to return
		RecruiterMangementResponseDTO response = RecruiterMangementResponseDTO.builder()
				.numberOfApplieds(numberOfApplieds).numberOfPostings(numberOfPostings).appliedCandidate(candidates)
				.lastestPostings(postings).build();
		return new ResponseEntity<RecruiterMangementResponseDTO>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/applied-candidate")
	public ResponseEntity<List<RecruiterAppliedJobResponseDTO>> findAllAppliedCandidate(
			@RequestBody RecruiterAppliedJobRequestDTO request) {
		Long recruiterId = request.getUserId();

		// 1. list of candidate who applied job's recruiter
		List<AppliedJob> appliedJobs = appliedJobService.findAppliedJobByRecruiterId(recruiterId);

		// format date
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		List<RecruiterAppliedJobResponseDTO> response = new ArrayList<RecruiterAppliedJobResponseDTO>();
		// 2. convert to dto
		for (AppliedJob appliedJob : appliedJobs) {
			User candidate = appliedJob.getCandidate();
			Posting posting = appliedJob.getPosting();

			RecruiterAppliedJobResponseDTO candidatePosting = RecruiterAppliedJobResponseDTO.builder()
					.candidateId(candidate.getId()).candidateName(candidate.getName()).postingId(posting.getId())
					.postingPosition(posting.getPosition())
					.appliedDate(dateFormat.format(appliedJob.getDateSubmission()))
					.commentFromEmployer(appliedJob.getCommentFromEmployer()).build();
			response.add(candidatePosting);
		}

		return new ResponseEntity<List<RecruiterAppliedJobResponseDTO>>(response, HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete-applied-candidate")
	public ResponseEntity<String> deleteAppliedCandidate(@RequestBody RecruiterDeleteAppliedJobRequestDTO request) {
		AppliedJobKey key = AppliedJobKey.builder().candidateId(request.getCandidateId())
				.recruiterId(request.getRecruiterId()).postingId(request.getPostingId()).build();
		appliedJobService.deleteAppliedJob(key);

		return new ResponseEntity<String>(ResponseMessage.DELETE_SUCCESS.getMessage(), HttpStatus.OK);
	}
}
