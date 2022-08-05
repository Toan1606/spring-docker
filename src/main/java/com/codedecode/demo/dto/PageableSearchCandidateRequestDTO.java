package com.codedecode.demo.dto;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageableSearchCandidateRequestDTO extends UserSearchAllFieldsRequestDTO{
	
	@Min(0)
	private int pageOffset;
	
}
