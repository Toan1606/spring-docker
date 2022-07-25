package com.codedecode.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class AddressRequestDTO {
	@JsonProperty("province_name")
	private String provinceName;

	@JsonProperty("city_name")
	private String cityName;
}
