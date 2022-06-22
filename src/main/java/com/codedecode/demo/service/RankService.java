package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.repository.RankRepository;
import com.codedecode.demo.utils.RankUtils;

@Service
public class RankService {
	
	@Autowired
	private RankRepository rankRepository;

//	public Rank addNewRank(Rank rank) {
//		RankUtils[] rankUtils = RankUtils.values();
//		for (RankUtils ru : rankUtils) {
//		}
//		return rankRepository.save(null);
//	}
}
