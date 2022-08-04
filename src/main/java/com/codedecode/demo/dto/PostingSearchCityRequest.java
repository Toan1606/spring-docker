package com.codedecode.demo.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingSearchCityRequest {
	
	@JsonProperty("page_off_set")
	private Integer pageOffSet;
	
	@NotBlank
	@JsonProperty("city_id")
	private Long cityId;
}
