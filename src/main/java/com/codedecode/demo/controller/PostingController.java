package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.service.PostingService;

@RestController
@RequestMapping("/posting")
public class PostingController {
	
	@Autowired
	private PostingService postingService;

	
	/*
	 * @author: Nguyễn Thế Toàn
	 * */
	@PutMapping("/add")
	public ResponseEntity<Posting> addNewPosting() {
		Posting posting = Posting.builder().jobName("NHÂN VIÊN IT (KCN SUỐI DẦU, CAM LÂM)").build();
		postingService.addPosting(posting);
		return new ResponseEntity<Posting>(posting, HttpStatus.CREATED);	
	}
	
	
}
