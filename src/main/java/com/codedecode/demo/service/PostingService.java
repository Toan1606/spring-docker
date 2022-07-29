package com.codedecode.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PostingResponseDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.exception.PostingNotFound;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingProjectionRepository;
import com.codedecode.demo.repository.PostingRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class PostingService {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private HomeAddressRepository addressRepository;

	@Autowired
	private PostingProjectionRepository postingProjectionRepository;
	
	public Iterable<Posting> getAttractiveJob() {
		System.out.println("findPosting function");
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

	public PostingResponseDTO findPostingByUserIdAndPostingId(Long userId, Long postingId) {
		PostingResponseDTO posting = postingProjectionRepository.findPostingByUserIdAndPostingId(userId, postingId);
		if (posting == null) {
			throw new PostingNotFound(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage());
		}
		return posting;
	}

	public void deletePostingById(Long id) {
		postingRepository.deleteById(id);
	}

	public PageDTO<Posting> searchPostingPage(String text, List<String> fields, int limit, int pageOffset) {
		return postingRepository.searchPageBy(text, limit, pageOffset, fields.toArray(new String[0]));
	}
	
}
