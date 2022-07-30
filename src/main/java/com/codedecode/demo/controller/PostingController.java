package com.codedecode.demo.controller;

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

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PageableSearchRequestDTO;
import com.codedecode.demo.dto.PostingRequestDTO;
import com.codedecode.demo.dto.PostingResponseDTO;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.service.PostingService;


@RestController
@RequestMapping("/posting")
@CrossOrigin(origins = "http://localhost:8080")
public class PostingController {

	@Autowired
	private PostingService postingService;
	
	
	
	/*
	 * @author : ToanNT16
	 * */
	@PostMapping("/{id}")
	public ResponseEntity<PostingResponseDTO> findPostingById(@RequestBody PostingRequestDTO postingRequestDTO) {
		Long userId = postingRequestDTO.getUserId();
		Long postingId = postingRequestDTO.getPostingId();
		PostingResponseDTO posting = postingService.findPostingByUserIdAndPostingId(userId, postingId);
		
		return new ResponseEntity<PostingResponseDTO>(posting, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deletePostingById(@PathVariable Long id) {
		postingService.deletePostingById(id);
		return ResponseEntity.ok(id);
	}
	
	@GetMapping("/search/page")
    public PageDTO<Posting> searchPlantPage(PageableSearchRequestDTO pageableSearchRequestDTO) {

        return postingService.searchPostingPage(pageableSearchRequestDTO.getText(), pageableSearchRequestDTO.getFields(), pageableSearchRequestDTO.getLimit(), pageableSearchRequestDTO.getPageOffset());
    }
}
