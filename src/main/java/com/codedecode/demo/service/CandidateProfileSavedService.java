package com.codedecode.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.entity.CandidateProfileSaved;
import com.codedecode.demo.repository.CandidateProfileSavedRepository;

@Service
@Transactional
public class CandidateProfileSavedService {
	private final CandidateProfileSavedRepository candidateProfileSavedRepository ;

	@Autowired
	public CandidateProfileSavedService(CandidateProfileSavedRepository candidateProfileSavedRepository) {
		this.candidateProfileSavedRepository = candidateProfileSavedRepository;
	}

	public CandidateProfileSaved saveCandidateProfileSaved(CandidateProfileSaved candidateProfileSaved) {
		return candidateProfileSavedRepository.save(candidateProfileSaved);
	}
	
}
