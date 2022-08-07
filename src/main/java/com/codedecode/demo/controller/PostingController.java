package com.codedecode.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.CityResponseDTO;
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
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Salary;
import com.codedecode.demo.entity.WorkingForm;
import com.codedecode.demo.entity.YearOfExperience;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.CityService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.SalaryService;
import com.codedecode.demo.service.StreetService;
import com.codedecode.demo.service.WorkingFormService;
import com.codedecode.demo.service.YearOfExperienceService;

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
	private YearOfExperienceService yearOfExperienceService;

	@Autowired
	private WorkingFormService workingFormService;

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

		Long yearOfExperienceId = posting.getYearOfExperienceId();

		Long workingFormId = posting.getWorkingFormId();

		String degreeRequired = posting.getDegreeRequired();

		// 2. query
		Salary salary = salaryService.findSalaryById(salaryId);

		YearOfExperience yearOfExperience = yearOfExperienceService.findYearOfExperienceById(yearOfExperienceId);

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

		response.setYearOfExperience(yearOfExperience.getName());

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

}
