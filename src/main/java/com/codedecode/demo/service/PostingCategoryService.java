package com.codedecode.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.repository.PostingCategoryRepository;

@Service
@Transactional
public class PostingCategoryService {

	@Autowired
	private PostingCategoryRepository postingCategoryRepository;
	
	public List<PostingCategory> findAll() {
		return postingCategoryRepository.findAll();
	}
}
