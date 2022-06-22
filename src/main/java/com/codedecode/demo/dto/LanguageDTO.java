package com.codedecode.demo.dto;

import java.util.List;

import com.codedecode.demo.entity.Language;

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
