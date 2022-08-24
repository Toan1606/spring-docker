package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.exception.RankNotFoundException;
import com.codedecode.demo.repository.RankRepository;

@Service
@Transactional
public class RankService {

	private final RankRepository rankRepository;

	@Autowired
	public  RankService(RankRepository rankRepository) {
		this.rankRepository = rankRepository;
	}

	public List<Rank> findAll() {
		return rankRepository.findAll();
	}
	
	public Rank findById(Long id) {
		return rankRepository.findById(id).orElseThrow(() -> new RankNotFoundException("Rank Not Found"));
	}
}
