package com.codedecode.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "FamilyMembers")
public class FamilyMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private FamilyMemberPrimaryKey familyMemberPrimaryKey;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "occupation")
	private String occupation;

	@Column(name = "august_revolution")
	private String augustRevolution;

	@Column(name = "resistance_war_against_French_colonialism")
	private String resistanceWarAgainstFrenchColonialism;

	@Column(name = "activities_from_1955")
	private String activitiesFrom1955;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "family_member_id", referencedColumnName = "family_member_id")
	private CurriculumVitae curriculumVitae;
}
