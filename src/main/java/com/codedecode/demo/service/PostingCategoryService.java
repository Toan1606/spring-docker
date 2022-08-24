package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.exception.PostingCategoryNotFound;
import com.codedecode.demo.repository.PostingCategoryRepository;

@Service
@Transactional
public class PostingCategoryService {


	private final PostingCategoryRepository postingCategoryRepository;

	@Autowired
	public PostingCategoryService(PostingCategoryRepository postingCategoryRepository) {
		this.postingCategoryRepository = postingCategoryRepository;
	}

	public List<PostingCategory> findAll() {
		return postingCategoryRepository.findAll();
	}
	
	public PostingCategory findById(Long postingCategoryId) {
		return postingCategoryRepository.findById(postingCategoryId).orElseThrow(() -> new PostingCategoryNotFound("Posting Category Not Found"));
	}
}
