package com.codedecode.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.repository.AppliedJobRepostory;

@Service
@Transactional
public class AppliedJobService {

	@Autowired
	private AppliedJobRepostory appliedJobRepostory;
	
	public int countNumberOfAppliedJob() {
		return appliedJobRepostory.countNumberOfAppliedJob();
	}
}
