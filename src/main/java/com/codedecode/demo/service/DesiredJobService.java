package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.CandidateProfileSaved;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.repository.CandidateProfileSavedRepository;
import com.codedecode.demo.repository.DesiredJobRepository;

@Service
@Transactional
public class DesiredJobService {

	
	@Autowired
	private DesiredJobRepository desiredJobRepository ;
	
	public DesiredJob saveDesiredJob(DesiredJob desiredJob) {
		return desiredJobRepository.save(desiredJob);
	}
	
}
