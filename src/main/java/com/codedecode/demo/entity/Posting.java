package com.codedecode.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Indexed
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

	@Column(name = "job_name")
	@FullTextField()
	private String jobName;

	@Column(name = "position")
	@FullTextField()
	private String position;

	@Column(name = "degree_required")
	private String degreeRequired;

	@Column(name = "quantity_needed")
	private String quantityNeeded;

	@Column(name = "descriptions", length = 3000)
	@FullTextField()
	private String description;

	@Column(name = "benefits", length = 3000)
	private String benefits;

	@Column(name = "profile_included", length = 1000)
	private String profileIncluded;

	@Column(name = "images", columnDefinition = "NVARCHAR(MAX)")
	private String images;

	@Column(name = "commission", length = 1000)
	private String commission;

	@Column(name = "probationary_period")
	private String probationaryPeriod;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "gender_requirement")
	@FullTextField()
	private String genderRequirement;

	@Column(name = "job_requirement", length = 4000)
	@FullTextField()
	private String jobRequirement;

	@Column(name = "deadline_for_submission")
	private String deadlineForSubmission;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "posting_category_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	@JsonIgnore
	private PostingCategory postingCategory;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "posting_type_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	@JsonIgnore
	private PostingType postingType;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "working_form_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private WorkingForm workingForm;

//	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinColumn(name = "year_of_experience_id", referencedColumnName = "id")
//	@EqualsAndHashCode.Exclude
//	@ToString.Exclude
//	@JsonIgnore
//	private YearOfExperience yearOfExperience;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "rank_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Rank rank;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "salary_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Salary salary;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "posting_address", joinColumns = @JoinColumn(name = "posting_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Address> addresss;

	@OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<SavedJob> savedJob;

	@OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<AppliedJob> appliedJob;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@NotNull
	@JsonIgnore
	private User user;

	@NotBlank
	@Column(name = "recruiter_name")
	private String recruiterName;

	@Column(name = "phone_number")
	@Size(min = 10, max = 10)
	@NotBlank(message = "mobileNumber is required")
	private String phoneNumber;

	@NotBlank
	@Column(name = "email_contact")
	private String emailContact;

	@Column(name = "views")
	private Long view;

	@Column(name = "files", length = 500)
	private String file;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "name : " + this.jobName;
	}
}
