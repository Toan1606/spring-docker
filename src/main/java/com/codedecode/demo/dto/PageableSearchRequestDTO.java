package com.codedecode.demo.dto;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PageableSearchRequestDTO extends PostingSearchRequestDTO {
	
	@Min(0)
	private int pageOffset;
}
