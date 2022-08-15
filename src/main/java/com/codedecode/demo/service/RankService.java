package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.repository.RankRepository;

@Service
@Transactional
public class RankService {
	
	@Autowired
	private RankRepository rankRepository;

	public List<Rank> findAll() {
		return rankRepository.findAll();
	}
}
