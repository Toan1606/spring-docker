package com.codedecode.demo.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationResponseDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	
	private String candidateName;
	
	

}
