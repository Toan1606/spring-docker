package com.codedecode.demo.dto;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserSearchAllFieldsRequestDTO {
	@NotBlank
	private String text;

	private List<String> fields = Arrays.asList("university", "careerGoals");
	
    private int limit = 4;
}
