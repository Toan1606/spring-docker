package com.codedecode.demo.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "FamilyMembers")
public class FamilyMember implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "curriculum_vitae_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private CurriculumVitae curriculumVitae;
}
