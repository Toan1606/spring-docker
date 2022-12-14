package com.codedecode.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codedecode.demo.dto.AddPostingRequestDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategory;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCity;
import com.codedecode.demo.dto.PostingSearchProvince;
import com.codedecode.demo.dto.SuitablePostingDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.AppliedJob;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.PostingType;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.exception.PostingNotFound;
import com.codedecode.demo.exception.PostingNotFoundException;
import com.codedecode.demo.repository.AppliedJobRepository;
import com.codedecode.demo.repository.HomeAddressRepository;
import com.codedecode.demo.repository.PostingRepository;
import com.codedecode.demo.utils.ExceptionMessage;

@Service
@Transactional

public class PostingService {

	private final UserService userService;

	private final PostingRepository postingRepository;

	private final HomeAddressRepository addressRepository;

	private final AppliedJobRepository appliedJobRepository;

	private final PostingCategoryService categoryService;

	private final RankService rankService;

	private final SalaryService salaryService;

	private final WorkingFormService formService;


	@Autowired
	public PostingService(UserService userService, PostingRepository postingRepository, HomeAddressRepository addressRepository, AppliedJobRepository appliedJobRepository, PostingCategoryService categoryService
	, RankService rankService, SalaryService salaryService, WorkingFormService formService) {
		this.userService = userService;
		this.postingRepository = postingRepository;
		this.addressRepository = addressRepository;
		this.appliedJobRepository = appliedJobRepository;
		this.categoryService = categoryService;
		this.rankService = rankService;
		this.salaryService = salaryService;
		this.formService = formService;
	}

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
	
	public int countNumberOfRecordsByProvince(Long provinceId) {
		return postingRepository.countNumberOfRecordsByProvince(provinceId);
	}
	
	public List<PostingSearchCategory> convertSearchByCategoryResult(List<PostingSearchCategoryResponseInterface> postings) {
		List<PostingSearchCategory> results = new ArrayList<PostingSearchCategory>();
		
		for(PostingSearchCategoryResponseInterface posting : postings ) {
			PostingSearchCategory reponse = new PostingSearchCategory();
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
	
	
	public List<PostingSearchProvince> searchPostingByProvince(Integer pageOffSet, Long provinceId) {
		int start = (pageOffSet - 1) * 30;
		int end = pageOffSet * 30;
		return postingRepository.findPostingByProvince(start, end, provinceId);
	}
	
	public List<PostingSearchCity> searchPostingByCity(Integer pageOffSet, Long cityId) {
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
				System.out.println("address: " +address.getId());
				System.out.println("user: " +user.getId());
			}
		}
		return postings;
	}

	public Posting addPostingRecruiter(AddPostingRequestDTO addPostingRequestDTO) {
		String email = addPostingRequestDTO.getEmail();
		String jobName = addPostingRequestDTO.getJobName();
		String jobRequirement = addPostingRequestDTO.getJobRequirement();
		String position = addPostingRequestDTO.getPosition();
		int quantity = addPostingRequestDTO.getQuantity();
		String description = addPostingRequestDTO.getDescription();
		String degreeRequired = addPostingRequestDTO.getDegreeRequired();
		String genderRequirement = addPostingRequestDTO.getGenderRequirement();
		String benefits = addPostingRequestDTO.getBenefits();
		String files = addPostingRequestDTO.getFiles();
		String deadlineForSubmission = addPostingRequestDTO.getDeadlineForSubmission();
		Long rankId = addPostingRequestDTO.getRank();
		Long formId = addPostingRequestDTO.getWorkingForm();
		Long salaryId = addPostingRequestDTO.getSalary();
		User user = userService.getUserByEmail(email);
		
		Random rand = new Random();
	      Long postingCategoryId = 70L;
		
		PostingCategory postingCategory = categoryService.findById(postingCategoryId);
		PostingType postingType = new PostingType(); 
		long view = 1;
		
		List<Address> address = new ArrayList<Address>();
		address.add(user.getAddress());
		
		Rank rank = rankService.findById(1L);
		Salary salary = salaryService.findSalaryById(salaryId);
		WorkingForm form = formService.findById(formId);
		
		
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
		posting.setImages(user.getImages());
		posting.setProfileIncluded(files);
		posting.setQuantityNeeded(quantity + "");
		posting.setJobName(jobName);
		posting.setJobRequirement(jobRequirement);
		posting.setSalary(salary);
		posting.setWorkingForm(form);
		posting.setRank(rank);
		posting.setAddresss(address);
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
		List<AppliedJob> appliedJobs = appliedJobRepository.getPostingById(id);
		Posting rs = postingRepository.fingPostingById(id);
		
		if(appliedJobs.size() > 0) {
			for(int i=0; i<appliedJobs.size(); i++) {
				appliedJobRepository.deleteAppliedJobByPostingId(id);
			}
		}
		
		postingRepository.deletePostingById(rs.getId());
	}
	
	public int countNumberOfRecordsByCity(Long cityId) {
		return postingRepository.countNumberOfRecordsByCity(cityId);
	}
	
	public int countNumberOfRecordsByCategory(Long categoryId) {
		return postingRepository.countNumberOfRecordsByCategory(categoryId);

	}
	
	public int countNumberOfPostingsByRecruiter(Long recruiterId) {
		return postingRepository.countByUser_Id(recruiterId);
	}
	
	public List<Posting> findLastestPostingByRecruiterId(Long recruiterId) {
		return postingRepository.findByUser_IdOrderByIdDesc(recruiterId);
	}
}
