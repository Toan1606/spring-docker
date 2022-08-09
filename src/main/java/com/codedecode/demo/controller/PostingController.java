package com.codedecode.demo.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.AddPostingRequestDTO;
import com.codedecode.demo.dto.CityResponseDTO;
import com.codedecode.demo.dto.GeneralManagementDTO;
import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PageableSearchRequestDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRelatedDTO;
import com.codedecode.demo.dto.PostingRequestDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.dto.PostingSearchCategoryRequest;
import com.codedecode.demo.dto.PostingSearchCategoryResponse;
import com.codedecode.demo.dto.PostingSearchCategoryResponseInterface;
import com.codedecode.demo.dto.PostingSearchCityRequest;
import com.codedecode.demo.dto.PostingSearchCityResponse;
import com.codedecode.demo.dto.PostingSearchProvinceRequest;
import com.codedecode.demo.dto.PostingSearchProvinceResponse;
import com.codedecode.demo.dto.SuitableJobDTO;
import com.codedecode.demo.dto.SuitablePostingDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.DesiredJob;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.AppliedJobService;
import com.codedecode.demo.service.CityService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.SalaryService;
import com.codedecode.demo.service.StreetService;
import com.codedecode.demo.service.UserService;
import com.codedecode.demo.service.WorkingFormService;

@RestController
@RequestMapping("/posting")
@CrossOrigin(origins = "http://localhost:8080")
public class PostingController {

	@Autowired
	private PostingService postingService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ProvinceService provinceService;

	@Autowired
	private CityService cityService;

	@Autowired
	private StreetService streetService;

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private WorkingFormService workingFormService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private AppliedJobService appliedJobService;

	/*
	 * @author : ToanNT16
	 */
	@PostMapping("/{id}")
	public ResponseEntity<PostingDetailResponse> findPostingById(@RequestBody PostingRequestDTO postingRequestDTO) {

		Long userId = postingRequestDTO.getUserId();

		Long postingId = postingRequestDTO.getPostingId();

		PostingResponseInterfaceDTO posting = postingService.findPostingByUserIdAndPostingId(userId, postingId);
		// 1. get top 10
		// 2. get address by posting
		// 3. get province by address
		// 4. convert to dto
		// 5. return

		Set<Address> addresss = addressService.findAddressByPostingId(postingId);

		// 1. get response
		Long salaryId = posting.getSalaryId();

		Long workingFormId = posting.getWorkingFormId();

		String degreeRequired = posting.getDegreeRequired();

		// 2. query
		Salary salary = salaryService.findSalaryById(salaryId);

		System.out.println("Salary : " + salary);

		WorkingForm workingForm = workingFormService.findWorkingFormById(workingFormId);

		List<Map<String, String>> provinces = provinceService.findByAddress(addresss);

		List<Map<String, String>> cities = cityService.findByAddress(addresss);

		List<String> streets = streetService.findByAddress(addresss);

		Set<String> setKey = provinces.get(0).keySet();
		String[] keys = setKey.toArray(new String[setKey.size()]);

		List<City> rawCities = cityService.findCityByProvinceId(Long.valueOf(provinces.get(0).get(keys[1])));

		List<CityResponseDTO> citiesDTO = cityService.convertToDTO(rawCities);

		List<PostingRelatedDTO> relatedPosting = postingService.findTop10RelatedPosting(posting.getPostingCategoryId());

		// 3. set response
		PostingDetailResponse response = postingService.convertPostingResponseDTO(posting);

		response.setDegreeRequired(degreeRequired);

		response.setSalary(salary.getName());

		response.setWorkingForm(workingForm.getName());

		response.setProvince(provinces);

		response.setCities(cities);

		response.setStreet(streets);

		response.setCitiesDto(citiesDTO);

		response.setRelatedPosting(relatedPosting);

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
	public ResponseEntity<List<PostingSearchCategoryResponse>> searchPostingByCategory(
			@RequestBody PostingSearchCategoryRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long postingCategoryId = request.getPostingCategoryId();
		if (pageOffSet == null) {
			pageOffSet = 1;
		}

		List<PostingSearchCategoryResponseInterface> postings = postingService.searchPostingByCategory(pageOffSet,
				postingCategoryId);
		List<PostingSearchCategoryResponse> response = postingService.convertSearchByCategoryResult(postings);
		return new ResponseEntity<List<PostingSearchCategoryResponse>>(response, HttpStatus.OK);
	}

	@PostMapping(path = "/province/{provinceId}")
	public ResponseEntity<List<PostingSearchProvinceResponse>> searchPostingByProvince(
			@RequestBody PostingSearchProvinceRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long provinceId = request.getProvinceId();
		if (pageOffSet == null) {
			pageOffSet = 1;
		}
		List<PostingSearchProvinceResponse> postings = postingService.searchPostingByProvince(pageOffSet, provinceId);
		return new ResponseEntity<List<PostingSearchProvinceResponse>>(postings, HttpStatus.OK);
	}

	@PostMapping(path = "/city/{cityId}")
	public ResponseEntity<List<PostingSearchCityResponse>> searchPostingByCity(
			@RequestBody PostingSearchCityRequest request) {
		Integer pageOffSet = request.getPageOffSet();
		Long cityId = request.getCityId();
		if (pageOffSet == null) {
			pageOffSet = 1;
		}
		List<PostingSearchCityResponse> postings = postingService.searchPostingByCity(pageOffSet, cityId);
		return new ResponseEntity<List<PostingSearchCityResponse>>(postings, HttpStatus.OK);
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
		List<Address> addresss = null;
		if (desiredJob == null) {
			addresss = new ArrayList<Address>();
			addresss.add(user.getAddress());
		} else {
			addresss = new ArrayList<Address>(desiredJob.getAddresss());
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
	public ResponseEntity<List<Posting>> getPostingByUserId(@PathVariable("userId") long id){
		return new ResponseEntity<List<Posting>>(postingService.getPostingByRecruiterId(id), HttpStatus.OK);
	}
}
