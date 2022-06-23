package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;

@Service
public class PostingService {

	@Autowired
	private PostingRepository postingRepository;
	
	@Autowired
	private HomeAddressRepository addressRepository;

	public Iterable<Posting> getAttractiveJob() {
		return postingRepository.getAllJob();
	}

	public Iterable<Posting> getUrgentJob() {
		return postingRepository.findAll();
	}
	
	public List<Address> getJobByProvice(){
		return addressRepository.getAllJobByProvince();
	}
	
	public Posting addPosting(Posting posting) {
		Posting returnPosting = postingRepository.save(posting);
		return returnPosting;
	}
}
