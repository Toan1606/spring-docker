package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.PostingRepository;

@Service
public class PostingService {

	@Autowired
	private PostingRepository postingRepository;

	public Iterable<Posting> getAttractiveJob() {
		return postingRepository.findAll();
	}

	public Iterable<Posting> getUrgentJob() {
		return postingRepository.findAll();
	}
}
