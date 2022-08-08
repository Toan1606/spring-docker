package com.codedecode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
@Builder
public class LanguageDTO {
	private Long id;
	private String certificate_name;
	private String name;
	private float mark;
	private Long userId;
}
