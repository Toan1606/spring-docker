package com.codedecode.demo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.codedecode.demo.entity.Posting;
import com.codedecode.demo.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterMangementResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numberOfApplieds;
	
	private int numberOfPostings;
	
	private Set<User> candidates;
	
	private List<Posting> lastestPostings;
}
