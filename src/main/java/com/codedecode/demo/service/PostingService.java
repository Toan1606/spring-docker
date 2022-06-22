package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.HomePostingRepository;

@Service
public class PostingService {

	@Autowired
	private HomePostingRepository homePostingRepository;
	
	@Autowired
	private HomeAddressRepository addressRepository;

	public Iterable<Posting> getAttractiveJob() {
		return homePostingRepository.getAllJob();
	}

	public Iterable<Posting> getUrgentJob() {
		return homePostingRepository.findAll();
	}
	
	public List<Address> getJobByProvice(){
		return addressRepository.getAllJobByProvince();
	}
	
	public Posting addPosting(Posting posting) {
		return homePostingRepository.save(posting);
	}
}
