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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Entity
@Table(name = "Posting")
public class Posting implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "position")
	private String position;
	
	@Column(name = "degree_required")
	private String degreeRequired;
	
	@Column(name = "quantity_needed")
	private String quantityNeeded;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "benefits")
	private String benefits;
	
	@Column(name = "profile_included")
	private String profileIncluded;
	
	@Column(name = "images")
	private String images;
	
	@Column(name = "commission")
	private String commission;
	
	@Column(name = "probationary_period")
	private String probationaryPeriod;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "gender_requirement")
	private String genderRequirement;
	
	@Column(name = "job_requirement")
	private String jobRequirement;
	
	@Column(name = "deadline_for_submission")
	private String deadlineForSubmission;

	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "year_of_experience_id", referencedColumnName = "id")
	private YearOfExperience yearOfExperience;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "rank_id", referencedColumnName = "id")
	private Rank rank;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "salary_id", referencedColumnName = "id")
	private Salary salary;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
}
