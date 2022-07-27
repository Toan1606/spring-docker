package com.codedecode.demo.dto;

import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostingSearchAllFieldsRequestDTO {
	@NotBlank
	private String text;

	private List<String> fields = Arrays.asList("address.provinces.name", "address.provinces.cities.name","jobRequirement", "genderRequirement", "salary.name",
			"workingForm.name", "position", "yearOfExperience.name", "jobName", "description");
	
    private int limit = 10;
}
