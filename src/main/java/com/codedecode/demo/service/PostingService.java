package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.AddPostingRequestDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingResponseDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategoryResponse;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCityResponse;
import com.codedecode.demo.dto.PostingSearchProvinceResponse;
import com.codedecode.demo.dto.SuitablePostingDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.PostingType;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.exception.PostingNotFound;
import com.codedecode.demo.exception.PostingNotFoundException;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional
public class PostingService {
	
	@Autowired
	private UserService userService;

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
	

	public SuitablePostingDTO convertToSuitablePosting(User user, Province province, Salary salary, Posting posting) {
		SuitablePostingDTO suitablePosting = new SuitablePostingDTO();
		
		// set
//		image	: Posting
//		position : Posting
//		companyName	: User
//		Province	: Posting
//		Salary	: Posting
//		DeadlineForSubmition	: Posting
		suitablePosting.setImages(posting.getImages());
		suitablePosting.setPosition(posting.getPosition());
		suitablePosting.setCompanyName(user.getName());
		suitablePosting.setProvince(province.getName());
		suitablePosting.setSalary(salary.getName());
		suitablePosting.setDeadLineForSubmission(posting.getDeadlineForSubmission());
		
		return suitablePosting;
	}
	
	
	public Set<SuitablePostingDTO> findPostingByAddress(List<Address> addresss) {
		Set<SuitablePostingDTO> postings = new HashSet<SuitablePostingDTO>();
		for (Address address : addresss) { 
			if (postings.size() == 6)	break;
			Set<Posting> posting = postingRepository.findByAddresss(address);
			for (Posting p : posting) {
				User user = p.getUser();
				Province province = address.getProvince();
				Salary salary = p.getSalary();
				SuitablePostingDTO suitablePosting = convertToSuitablePosting(user, province, salary, p);
				postings.add(suitablePosting);
			}
		}
		return postings;
	}

	public Posting addPostingRecruiter(AddPostingRequestDTO addPostingRequestDTO) {
		String email = addPostingRequestDTO.getEmail();
		String position = addPostingRequestDTO.getPosition();
		long workingForm = addPostingRequestDTO.getWorkingForm();
		long salary = addPostingRequestDTO.getSalary();
		int quantity = addPostingRequestDTO.getQuantity();
		String description = addPostingRequestDTO.getDescription();
		String degreeRequired = addPostingRequestDTO.getDegreeRequired();
		String genderRequirement = addPostingRequestDTO.getGenderRequirement();
		String benefits = addPostingRequestDTO.getBenefits();
		String files = addPostingRequestDTO.getFiles();
		String deadlineForSubmission = addPostingRequestDTO.getDeadlineForSubmission();
		User user = userService.getUserByEmail(email);
		PostingCategory postingCategory = new PostingCategory();
		PostingType postingType = new PostingType(); 
		long view = 1;
		
		Posting posting = new Posting();
		posting.setUser(user);
		posting.setPosition(position);
		posting.setWorkingForm(null);
		posting.setSalary(null);
		posting.setQuantity(quantity);
		posting.setDescription(description);
		posting.setDegreeRequired(degreeRequired);
		posting.setGenderRequirement(genderRequirement);
		posting.setBenefits(benefits);
		posting.setFile(files);
		posting.setDeadlineForSubmission(deadlineForSubmission);
		posting.setRecruiterName(user.getName());
		posting.setPhoneNumber(user.getPhone());
		posting.setEmailContact(user.getEmail());
		posting.setPostingCategory(postingCategory);
		posting.setPostingType(postingType);
		posting.setView(view);
		return postingRepository.save(posting);
	}
	
	public List<Posting> getPostingByRecruiterId(long recruiterId){
		List<Posting> list = postingRepository.fingPostingByUserId(recruiterId);
		if(list.size() > 0) {
			return list;
		} 
		return null;
  }

	public Posting findPostingById(Long postingId) {
		
		return postingRepository.findById(postingId).orElseThrow(() -> new PostingNotFoundException(ExceptionMessage.POSTING_NOT_FOUND.getErrorMessage()));
	}
	
	public Posting updatePostingByRecruiterId(long id, Posting posting) {
		Posting rs = postingRepository.fingPostingById(id);
		if(rs != null) {
			rs.setPosition(posting.getPosition());
			rs.setQuantity(posting.getQuantity());
			rs.setDegreeRequired(posting.getDegreeRequired());
			rs.setGenderRequirement(posting.getGenderRequirement());
			rs.setBenefits(posting.getBenefits());
			rs.setDescription(posting.getDescription());
			rs.setFile(posting.getFile());
			rs.setDeadlineForSubmission(posting.getDeadlineForSubmission());
		}
		return rs;
	}
	
	public void recruiterDeletePostingById(long id) {
		Posting rs = postingRepository.fingPostingById(id);
		postingRepository.deletePostingById(rs.getId());
	}
}
