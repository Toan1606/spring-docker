package com.codedecode.demo.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.demo.dto.CandidateContactInfoDTO;
import com.codedecode.demo.entity.Address;
import com.codedecode.demo.entity.City;
import com.codedecode.demo.entity.Province;
import com.codedecode.demo.entity.Street;
import com.codedecode.demo.entity.User;
import com.codedecode.demo.service.AddressService;
import com.codedecode.demo.service.ProvinceDistrictService;
import com.codedecode.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/contactinfo")
@Transactional
public class CandidateContactInfoController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private ProvinceDistrictService provinceDistrictService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> showContactInfoPage(@PathVariable Long userId){
		User user = userService.findUserById(userId);
		Address addressOfUser = user.getAddress();
//		Address address = addressService.findAddressByProvinceAndCity(addressOfUser.getProvince().getId(), addressOfUser.getCity().getId());
		CandidateContactInfoDTO contactinfo = new CandidateContactInfoDTO();
		contactinfo.setUserId(userId);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
		contactinfo.setDateOfBirth(dateFormat.format(user.getBirthDate()));
		contactinfo.setFullname(user.getName());
		contactinfo.setEmail(user.getEmail());
		contactinfo.setGender(user.getGender());
		contactinfo.setPhoneNumber(user.getPhone());
		contactinfo.setMarried(user.getMariaStatus());
		contactinfo.setAddress(addressOfUser.getStreet().getName());
		contactinfo.setProvince(addressOfUser.getProvince().getName());
		contactinfo.setDistrict(addressOfUser.getCity().getName());
		contactinfo.setDistrictId(addressOfUser.getCity().getId());
		contactinfo.setProvinceId(addressOfUser.getProvince().getId());
		contactinfo.setImageBase64(user.getImages());
		return new ResponseEntity<CandidateContactInfoDTO>(contactinfo, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> saveContactInfo(@RequestBody CandidateContactInfoDTO contactinfoDTO){
		User user = userService.findUserById(contactinfoDTO.getUserId());
		user.setName(contactinfoDTO.getFullname());
		user.setEmail(contactinfoDTO.getEmail());
		user.setPhone(contactinfoDTO.getPhoneNumber());
		user.setBirthDate(formatDate(contactinfoDTO.getDateOfBirth()));
		user.setGender(contactinfoDTO.getGender());
		user.setMariaStatus(contactinfoDTO.getMarried());
		user.setImages(contactinfoDTO.getImageBase64());
		Province province = provinceDistrictService.findProvinceById(contactinfoDTO.getProvinceId());
		City city = provinceDistrictService.findDistrictById(contactinfoDTO.getDistrictId());
		Address address = user.getAddress();
		address.getStreet().setName(contactinfoDTO.getAddress());
		address.setCity(city);
		address.setProvince(province);
		user.setAddress(address);
		userService.updateUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	public Date formatDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFormat = new Date();
		try {
			dateFormat = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat;
	}
}
