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

	@PutMapping("/add/posting")
	public ResponseEntity<Posting> addNewPosting() {
		Posting posting = Posting.builder().recruiterName("FPT Software").phoneNumber("0123456789")
				.emailContact("ericnguyen1606@gmail.com").position("Leader").degreeRequired("college degree")
				.quantity(10)
				.description("- Hoàn thành những mục tiêu đặt ra của dự án\r\n"
						+ "- Đảm bảo hoàn thành công việc theo sự phân công của cấp trên\r\n"
						+ "- Chi tiết công việc trao đổi khi phỏng vấn")
				.benefits("Được làm việc trong môi trường trẻ trung, hiện đại, chuyên nghiệp\r\n"
						+ "- Được đào tạo thường xuyên, không ngừng phát triển bản thân\r\n")
				.build();
		postingService.addPosting(posting);
		return new ResponseEntity<Posting>(posting, HttpStatus.CREATED);
	}

	@PutMapping("/add/posting/test")
	public ResponseEntity<String> addNewPostingTest() {
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
