package com.codedecode.demo.entity.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@EqualsAndHashCode
@Embeddable
public class CurriculumVitaePrimaryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@Column(name = "family_member_id")
	private Long familyMemberId;
	
	@Column(name = "even_id")
	private Long evenId;
}
