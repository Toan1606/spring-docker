package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreetDTO {
	
	@JsonProperty("street_id")
	private Long id;
	
	@JsonProperty("street_name")
	private String name;
	
	@JsonProperty("street_type")
	private String type;
}
