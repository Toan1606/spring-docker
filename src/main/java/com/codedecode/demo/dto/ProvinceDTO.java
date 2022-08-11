package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProvinceDTO {
	
	@JsonProperty("province_id")
	private Long id;
	
	@JsonProperty("province_name")
	private String name;
	
}
