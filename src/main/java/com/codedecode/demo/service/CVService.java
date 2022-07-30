package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.CV;
import com.codedecode.demo.repository.CVRepository;

@Service
public class CVService {
	@Autowired
	private CVRepository cvRepository;
	
	
	/*
	 * 
	 * @author: TuanNV
	 * 
	 * */
	public CV getCVById(Long id) {
		return cvRepository.getCVById(id);
	}
	/*
	 * 
	 * @author: TuanNV
	 * 
	 * */
	public List<CV> getCVsByUserId(Long id) {
		return cvRepository.getCVsByUserId(id);
	}
	
}
