package com.codedecode.demo.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostingSearchCategoryRequest {
	
	@JsonProperty("page_off_set")
	private Integer pageOffSet;
	
	@NotBlank
	@JsonProperty("posting_category_id")
	private Long postingCategoryId;
}
