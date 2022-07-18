package com.codedecode.demo.dto;

import java.util.Arrays;
import java.util.List;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostingSearchRequestDTO {
	@NotBlank
	private String text;

	private List<String> fields = Arrays.asList("jobName", "position", "description", "jobRequirement");
	
    private int limit = 10;
}
