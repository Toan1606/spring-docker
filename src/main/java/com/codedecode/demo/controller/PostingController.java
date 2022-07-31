package com.codedecode.demo.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.PageDTO;
import com.codedecode.demo.dto.PageableSearchRequestDTO;
import com.codedecode.demo.dto.PostingDetailResponse;
import com.codedecode.demo.dto.PostingRequestDTO;
import com.codedecode.demo.dto.PostingResponseInterfaceDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.CityService;
import com.codedecode.demo.service.PostingService;
import com.codedecode.demo.service.ProvinceService;
import com.codedecode.demo.service.StreetService;


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
	/*
	 * @author : ToanNT16
	 * */
	@PostMapping("/{id}")
	public ResponseEntity<PostingDetailResponse> findPostingById(@RequestBody PostingRequestDTO postingRequestDTO) {
		Long userId = postingRequestDTO.getUserId();
		Long postingId = postingRequestDTO.getPostingId();
		PostingResponseInterfaceDTO posting = postingService.findPostingByUserIdAndPostingId(userId, postingId);
		List<Address> addresss = addressService.findAddressByPostingId(postingId);
		
		for (Address address : addresss) {
			Province province = address.getProvince();
			City city = address.getCity();
			Street street = address.getStreet();
			if (province != null) {
				System.out.println(province.getName());
			}
			
			if (city != null) {
				System.out.println(city.getName());
			}
			
			if (street != null) {
				System.out.println(street.getName());
			}
		}
		
		List<String> provinces = provinceService.findByAddress(addresss);
		
		List<String> cities = cityService.findByAddress(addresss);
		
		List<String> streets = streetService.findByAddress(addresss);
		
		PostingDetailResponse response = postingService.convertPostingResponseDTO(posting);
		
		response.setProvince(provinces);
		
		response.setProvince(cities);
		
		response.setProvince(streets);
		
		return new ResponseEntity<PostingDetailResponse>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deletePostingById(@PathVariable Long id) {
		postingService.deletePostingById(id);
		return ResponseEntity.ok(id);
	}
	
	@GetMapping("/search/page")
    public PageDTO<Posting> searchPlantPage(PageableSearchRequestDTO pageableSearchRequestDTO) {

        return postingService.searchPostingPage(pageableSearchRequestDTO.getText(), pageableSearchRequestDTO.getFields(), pageableSearchRequestDTO.getLimit(), pageableSearchRequestDTO.getPageOffset());
    }
}
