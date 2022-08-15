package com.codedecode.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.AddPostingRequestDTO;
import com.codedecode.demo.dto.CityResponseDTO;
import com.codedecode.demo.dto.GeneralManagementDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PageableSearchRequestDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingRequestDTO;
import com.codedecode.demo.dto.PostingSearchCategory;
import com.codedecode.demo.dto.PostingSearchCategoryRequest;
import com.codedecode.demo.dto.PostingSearchCategoryResponse;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCity;
import com.codedecode.demo.dto.PostingSearchCityRequest;
import com.codedecode.demo.dto.PostingSearchCityResponse;
import com.codedecode.demo.dto.PostingSearchProvince;
import com.codedecode.demo.dto.PostingSearchProvinceRequest;
import com.codedecode.demo.dto.PostingSearchProvinceResponse;
import com.codedecode.demo.dto.SuitableJobDTO;
import com.codedecode.demo.dto.SuitablePostingDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.PostingCategory;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Rank;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.service.AppliedJobService;
import com.codedecode.demo.service.CityService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.UserService;

@RestController
@RequestMapping("/posting")
@CrossOrigin(origins = "http://localhost:8080")
public class PostingController {

	@Autowired
	private PostingService postingService;

	@Autowired
	private UserService userService;

	@Autowired
	private AppliedJobService appliedJobService;

	@Autowired
	private CityService cityService;

	/*
	 * @author : ToanNT16
	 */
	@PostMapping("/{id}")
	public ResponseEntity<PostingDetailResponse> findPostingById(@RequestBody PostingRequestDTO postingRequestDTO) {

		Long postingId = postingRequestDTO.getPostingId();

//		PostingResponseInterfaceDTO posting = postingService.findPostingByUserIdAndPostingId(userId, postingId);
		Posting posting = postingService.findPostingById(postingId);
		// 1. get top 10
		// 2. get address by posting
		// 3. get province by address
		// 4. get rank
		// 5. get working form
		// 6. get user
		// 4. convert to dto
		// 5. return

		// 1. get response
		Salary salary = posting.getSalary();

		WorkingForm workingForm = posting.getWorkingForm();

		// 2. query
		List<Address> addresss = new ArrayList<Address>(posting.getAddresss());

		// province
		List<Map<String, String>> provinces = new ArrayList<Map<String, String>>();
		// city
		List<Map<String, String>> cities = new ArrayList<Map<String, String>>();

		List<CityResponseDTO> citiesDto = cityService.findAllCityDto();
		// street
		List<String> streets = new ArrayList<String>();

		for (Address address : addresss) {

			// province
			Map<String, String> provinceMap = new HashMap<String, String>();
			Province province = address.getProvince();
			provinceMap.put("id", String.valueOf(province.getId()));
			provinceMap.put("name", String.valueOf(province.getName()));

			provinces.add(provinceMap);

			// city
			Map<String, String> cityMap = new HashMap<String, String>();
			City city = address.getCity();
			cityMap.put("id", String.valueOf(city.getId()));
			cityMap.put("name", String.valueOf(city.getName()));

			// city dto
			citiesDto.add(new CityResponseDTO(city.getId(), city.getName()));

			cities.add(cityMap);

			// street
			Street street = address.getStreet();
			streets.add(street.getName());
		}

		PostingCategory postingCategory = posting.getPostingCategory();

		List<PostingRelatedDTO> relatedPosting = postingService.findTop10RelatedPosting(postingCategory.getId());

		Rank rank = posting.getRank();

		User user = posting.getUser();

		// 3. set response
		PostingDetailResponse response = PostingDetailResponse.builder().id(postingId).recruiterEmail(user.getEmail())
				.benefits(posting.getBenefits()).commission(posting.getCommission())
				.deadlineForSubmission(posting.getDeadlineForSubmission()).degreeRequired(posting.getDegreeRequired())
				.description(posting.getDescription()).emailContact(posting.getEmailContact()).file(posting.getFile())
				.genderRequirement(posting.getGenderRequirement()).images(posting.getImages())
				.jobName(posting.getJobName()).jobRequirement(posting.getJobRequirement())
				.phoneNumber(posting.getPhoneNumber()).position(posting.getPosition())
				.profileIncluded(posting.getProfileIncluded()).quantity(posting.getQuantity())
				.quantityNeeded(posting.getQuantityNeeded()).view(posting.getView())
				.rankId(rank == null ? null : rank.getId()).salaryId(salary == null ? null : salary.getId())
				.workingFormId(workingForm == null ? null : workingForm.getId())
				.workingForm(workingForm == null ? null : workingForm.getName()).companyId(user.getId())
				.companyName(user.getName()).postingCategoryId(postingCategory.getId())
				.postingCategoryName(postingCategory.getCategoryName()).salary(salary == null ? null : salary.getName())
				.province(provinces).cities(cities).street(streets).citiesDto(citiesDto).relatedPosting(relatedPosting)
				.build();

		return new ResponseEntity<PostingDetailResponse>(response, HttpStatus.OK);
	}

	/*
	 * @author : ToanNT16
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deletePostingById(@PathVariable Long id) {
		postingService.deletePostingById(id);
		return ResponseEntity.ok(id);
	}

	/*
	 * @author : ToanNT16
	 */
	@GetMapping(path = "/searchJob")
	public PageDTO<Posting> searchPlantPage(PageableSearchRequestDTO pageableSearchRequestDTO) {

		return postingService.searchPostingPage(pageableSearchRequestDTO.getText(),
				pageableSearchRequestDTO.getFields(), pageableSearchRequestDTO.getLimit(),
				pageableSearchRequestDTO.getPageOffset());
	}

	@PostMapping(path = "/category/{categoryId}")
	public ResponseEntity<PostingSearchCategoryResponse> searchPostingByCategory(
			@RequestBody PostingSearchCategoryRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long postingCategoryId = request.getPostingCategoryId();
		System.out.println("pageOffSet : " + pageOffSet);
		if (pageOffSet == null) {
			pageOffSet = 1;
		}

		List<PostingSearchCategoryResponseInterface> postings = postingService.searchPostingByCategory(pageOffSet,
				postingCategoryId);
		List<PostingSearchCategory> postingsSearch = postingService.convertSearchByCategoryResult(postings);

		int numberOfRecords = postingService.countNumberOfRecordsByCategory(postingCategoryId);

		PostingSearchCategoryResponse response = PostingSearchCategoryResponse.builder().response(postingsSearch)
				.numberOfRecords(numberOfRecords).postingCategoryId(postingCategoryId).build();

		return new ResponseEntity<PostingSearchCategoryResponse>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/province/{provinceId}")
	public ResponseEntity<PostingSearchProvinceResponse> searchPostingByProvince(
			@RequestBody PostingSearchProvinceRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long provinceId = request.getProvinceId();

		System.out.println("pageOffSet : " + pageOffSet);
		if (pageOffSet == null) {
			pageOffSet = 1;
		}

		List<PostingSearchProvince> postings = postingService.searchPostingByProvince(pageOffSet, provinceId);
		int numberOfRecords = postingService.countNumberOfRecordsByProvince(provinceId);

		PostingSearchProvinceResponse response = PostingSearchProvinceResponse.builder().postings(postings)
				.numberOfRecords(numberOfRecords).provinceId(provinceId).build();

		return new ResponseEntity<PostingSearchProvinceResponse>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/city/{cityId}")
	public ResponseEntity<PostingSearchCityResponse> searchPostingByCity(
			@RequestBody PostingSearchCityRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long cityId = request.getCityId();

		System.out.println("pageOffSet : " + pageOffSet);
		if (pageOffSet == null) {
			pageOffSet = 1;
		}
		List<PostingSearchCity> postings = postingService.searchPostingByCity(pageOffSet, cityId);
		int numberOfRecords = postingService.countNumberOfRecordsByCity(cityId);

		PostingSearchCityResponse response = PostingSearchCityResponse.builder().postings(postings)
				.numberOfRecords(numberOfRecords).cityId(cityId).build();

		return new ResponseEntity<PostingSearchCityResponse>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/suitable")
	public ResponseEntity<GeneralManagementDTO> findTop6SuitablePosting(@RequestBody SuitableJobDTO request) {
		GeneralManagementDTO generalManagementDTO = new GeneralManagementDTO();
		String email = request.getEmail();
		// suitable job by address
		// case 1 : address of desired job
		// case 2 : address of posting
		// step 1. find user

		User user = userService.findUserByEmail(email);
		// step 2. get desired job
		DesiredJob desiredJob = user.getDesiredJob();

		Set<SuitablePostingDTO> postings = null;
		List<Address> addresss = new ArrayList<Address>();
		if (desiredJob == null) {
			addresss = new ArrayList<Address>();
			addresss.add(user.getAddress());
		} else {
			Collection<Address> collectionAddresss = desiredJob.getAddresss();
			Iterator<Address> iteratorAddress = collectionAddresss.iterator();
			while (iteratorAddress.hasNext()) {
				addresss.add(iteratorAddress.next());
			}
		}
		postings = postingService.findPostingByAddress(addresss);

		int numberOfAppliedJob = appliedJobService.countNumberOfAppliedJob();
		int numberOfSuitableJob = postings.size() * 3;
		generalManagementDTO.setSuitableJob(postings);
		generalManagementDTO.setNumberOfAppliedJob(numberOfAppliedJob);
		generalManagementDTO.setNumberOfSuitableJob(numberOfSuitableJob);

		return new ResponseEntity<GeneralManagementDTO>(generalManagementDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/addPosting")
	public ResponseEntity<Posting> addPosting(@RequestBody AddPostingRequestDTO addPostingRequestDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postingService.addPostingRecruiter(addPostingRequestDTO));

	}

	@GetMapping(value = "/getPosting/{userId}")
	public ResponseEntity<List<Posting>> getPostingByUserId(@PathVariable("userId") long id) {
		return new ResponseEntity<List<Posting>>(postingService.getPostingByRecruiterId(id), HttpStatus.OK);
	}

	@PutMapping(value = "/updatePosting/{userId}")
	public ResponseEntity<Posting> updatePostingByUserId(@PathVariable("userId") long id,
			@RequestBody Posting posting) {
		return new ResponseEntity<Posting>(postingService.updatePostingByRecruiterId(id, posting), HttpStatus.OK);
	}

	@GetMapping(value = "/updatePosting/{id}")
	public ResponseEntity<Posting> getPostingById(@PathVariable("id") long id) {
		return new ResponseEntity<Posting>(postingService.findPostingById(id), HttpStatus.OK);
	}

	@DeleteMapping(value = "/updatePosting/{id}")
	public ResponseEntity<Posting> deletePostingById(@PathVariable("id") long id) {
		postingService.recruiterDeletePostingById(id);
		return new ResponseEntity<Posting>(HttpStatus.OK);
	}

}
