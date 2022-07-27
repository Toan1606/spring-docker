package com.codedecode.demo.dto;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostingSearchGenderRequestDTO {
	@NotBlank
	private String text;

	private List<String> fields = Arrays.asList("genderRequirement");
	
    private int limit = 10;
}
