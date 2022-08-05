package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategoryResponse;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCityResponse;
import com.codedecode.demo.dto.PostingSearchProvinceResponse;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.exception.PostingNotFound;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class PostingService {

	@Autowired
	private PostingRepository postingRepository;

	@Autowired
	private HomeAddressRepository addressRepository;

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

	public PostingResponseInterfaceDTO findPostingByUserIdAndPostingId(Long userId, Long postingId) {
		PostingResponseInterfaceDTO posting = postingRepository.findPostingByUserIdAndPostingId(userId, postingId);
		if (posting == null) {
			throw new PostingNotFound(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage());
		}
		return posting;
	}
	
	public PostingDetailResponse convertPostingResponseDTO(PostingResponseInterfaceDTO postingInterface) {
		
		PostingDetailResponse posting = PostingDetailResponse.builder()
		.benefits(postingInterface.getBenefits())
		.commission(postingInterface.getCommission())
		.deadlineForSubmission(postingInterface.getDeadlineForSubmission())
		.degreeRequired(postingInterface.getDegreeRequired())
		.description(postingInterface.getDescription())
		.emailContact(postingInterface.getEmailContact())
		.file(postingInterface.getFiles())
		.genderRequirement(postingInterface.getGenderRequirement())
		.images(postingInterface.getImage())
		.jobName(postingInterface.getJobName())
		.jobRequirement(postingInterface.getJobRequirement())
		.phoneNumber(postingInterface.getPhoneNumber())
		.position(postingInterface.getPosition())
		.profileIncluded(postingInterface.getProfileIncluded())
		.quantity(postingInterface.getQuantity())
		.quantityNeeded(postingInterface.getQuantityNeeded())
		.view(postingInterface.getViews())
		.rankId(postingInterface.getRankId())
		.salaryId(postingInterface.getSalaryId())
		.workingFormId(postingInterface.getWorkingFormId())
		.yearOfExperienceId(postingInterface.getYearOfExperienceId())
		.companyId(postingInterface.getCompanyId())
		.companyName(postingInterface.getCompanyName())
		.postingCategoryId(postingInterface.getPostingCategoryId())
		.postingCategoryName(postingInterface.getPostingCategoryName())
		.build();
		return posting;
	}

	public void deletePostingById(Long id) {
		postingRepository.deleteById(id);
	}

	public PageDTO<Posting> searchPostingPage(String text, List<String> fields, int limit, int pageOffset) {
		return postingRepository.searchPageBy(text, limit, pageOffset, fields.toArray(new String[0]));
	}
	
	public List<PostingRelatedDTO> findTop10RelatedPosting(Long postingCategoryId) {
		return postingRepository.findTop10RelatedPosting(postingCategoryId);
	}

	public List<PostingSearchCategoryResponseInterface> searchPostingByCategory(Integer pageOffSet, Long postingCategoryId) {
		int start = (pageOffSet - 1) * 30;
		int end = pageOffSet * 30;
		return postingRepository.findPostingByCategory(start, end, postingCategoryId);
	}
	
	public List<PostingSearchCategoryResponse> convertSearchByCategoryResult(List<PostingSearchCategoryResponseInterface> postings) {
		List<PostingSearchCategoryResponse> results = new ArrayList<PostingSearchCategoryResponse>();
		
		for(PostingSearchCategoryResponseInterface posting : postings ) {
			PostingSearchCategoryResponse reponse = new PostingSearchCategoryResponse();
			reponse.setRowNumber(posting.getRowNumber());
			reponse.setImages(posting.getImages());
			reponse.setPosition(posting.getPosition());
			reponse.setPostingId(posting.getPostingId());
			reponse.setJobName(posting.getJobName());
			reponse.setDeadlineForSubmission(posting.getDeadlineForSubmission());
			reponse.setCompanyId(posting.getCompanyId());
			reponse.setCompanyName(posting.getcompanyName());
			reponse.setSalary(posting.getSalary());
			reponse.setProvince(posting.getProvince());
			reponse.setPostingCategoryId(posting.getPostingCategoryId());
			results.add(reponse);
		}
		return results;
	}
	
	
	public List<PostingSearchProvinceResponse> searchPostingByProvince(Integer pageOffSet, Long provinceId) {
		int start = (pageOffSet - 1) * 30;
		int end = pageOffSet * 30;
		return postingRepository.findPostingByProvince(start, end, provinceId);
	}
	
	public List<PostingSearchCityResponse> searchPostingByCity(Integer pageOffSet, Long cityId) {
		int start = (pageOffSet - 1) * 30;
		int end = pageOffSet * 30;
		return postingRepository.findPostingByCity(start, end, cityId);
	}
}
