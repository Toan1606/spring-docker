package com.codedecode.demo.entity.paging;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Component
public class StudentPage {
	private int pageNumber = 0;
	private int pageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "name";
}
