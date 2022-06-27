package com.codedecode.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@Builder
public class LanguageDTO {
	private String certificate_name;
	private String name;
	private String mark;
	private int userId;
}
