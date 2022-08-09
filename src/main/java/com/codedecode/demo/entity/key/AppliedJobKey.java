package com.codedecode.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AppliedJobKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long candidateId;
	
	private Long recruiterId;
	
	private Long postingId;
	

}
