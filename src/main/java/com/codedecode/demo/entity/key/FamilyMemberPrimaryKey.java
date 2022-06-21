package com.codedecode.demo.entity.key;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@EqualsAndHashCode
public class FamilyMemberPrimaryKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
//	@ManyToOne
//	@JoinColumn(name = "family_member_id", referencedColumnName = "family_member_id")
//	private CurriculumVitae curriculumVitae;
}
