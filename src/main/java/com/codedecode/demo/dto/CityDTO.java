package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDTO {
	@JsonProperty("city_id")
	private Long id;
	
	@JsonProperty("city_name")
	private String name;
	
	@JsonProperty("city_type")
	private String type;
}
