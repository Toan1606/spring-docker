package com.codedecode.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.utils.Message;


@RestController
@RequestMapping("/posting")
@CrossOrigin(origins = "http://localhost:8080")
public class PostingController {

	@Autowired
	private PostingService postingService;

	/*
	 * @author: Nguyễn Thế Toàn
	 */
	@PostMapping("/add")
	public ResponseEntity<Posting> addNewPosting() {

		Posting posting = Posting.builder().recruiterName("FPT Software").phoneNumber("0123456789")
				.emailContact("ericnguyen1606@gmail.com").build();
		Posting returnPosting = postingService.addPosting(posting);
		System.err.println(returnPosting);
		return new ResponseEntity<Posting>(returnPosting, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findPostingById(@PathVariable Long id) {
		Posting posting = postingService.findPostingById(id);

		if (posting == null)
			return new ResponseEntity<String>(Message.POSTING_NOT_EXIST, HttpStatus.OK);

		return new ResponseEntity<Posting>(posting, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deletePostingById(@PathVariable Long id) {
		postingService.deletePostingById(id);
		return ResponseEntity.ok(id);
	}
}
