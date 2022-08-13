package com.codedecode.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CandidateContactInfoDTO {

	private Long userId;
	private String fullname;
	private String email;
	private String phoneNumber;
	private String dateOfBirth;
	private String address;
	private String gender;
	private String province;
	private String district;
	private String married;
	private String imageBase64;
	private Long provinceId;
	private Long districtId;
}
