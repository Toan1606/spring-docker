package com.codedecode.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.PostingHomePage;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.service.PostingService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/home")
public class HomePageController {

	@Autowired
	private PostingService postingService;

	@GetMapping("/")
	public ResponseEntity<PostingHomePage> homePage() {
		List<Posting> attractiveJob = StreamSupport.stream(postingService.getAttractiveJob().spliterator(), false)
				.collect(Collectors.toList());
		List<Posting> urgentJob = StreamSupport.stream(postingService.getUrgentJob().spliterator(), false)
				.collect(Collectors.toList());

		PostingHomePage postingHomePage = PostingHomePage.builder().attractiveJob(attractiveJob)
				.urgentRecruitment(urgentJob).build();
		
		return new ResponseEntity<PostingHomePage>(postingHomePage, HttpStatus.OK);
	}

	@PutMapping("/add/posting/test")
	public ResponseEntity<String> addNewPostingTest() {
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
