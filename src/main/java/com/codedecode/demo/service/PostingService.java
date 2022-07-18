package com.codedecode.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;

@Service
@Transactional
public class PostingService {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private HomeAddressRepository addressRepository;

	public Iterable<Posting> getAttractiveJob() {
		return postingRepository.findAll();
	}

	public Iterable<Posting> getUrgentJob() {
		return postingRepository.findAll();
	}

	public List<Address> getJobByProvice() {
		return addressRepository.findAddress();
	}

	public Posting addPosting(Posting posting) {
		Posting returnPosting = postingRepository.save(posting);
		return returnPosting;
	}

	public Posting findPostingById(Long id) {
		Optional<Posting> optionalPosting = postingRepository.findById(id);
		Posting posting = optionalPosting.isPresent() ? optionalPosting.get() : null;

		if (posting == null)
			return null;

		return posting;
	}

	public void deletePostingById(Long id) {
		postingRepository.deleteById(id);
	}

	public PageDTO<Posting> searchPostingPage(String text, List<String> fields, int limit, int pageOffset) {
		return postingRepository.searchPageBy(text, limit, pageOffset, fields.toArray(new String[0]));
	}

}
