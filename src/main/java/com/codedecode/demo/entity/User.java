package com.codedecode.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "email", unique = true, length = 100)
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<CV> cv;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<JobApplication> jobApplication;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<CurriculumVitae> curriculumVitae;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<CoverLetter> coverLetter;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	@ToString.Exclude
	@JsonIgnore
	private Address address;

	@Column(name = "password")
	private String password;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "career_goals")
	private String careerGoals;

	@Column(name = "university")
	private String university;

	@Column(name = "rating")
	private String rating;

	@Column(name = "phone")
	private String phone;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Notification> notifications;

	@Column(name = "maria_status")
	private String mariaStatus;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Degree> degrees;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Posting> postings;

	@Column(name = "gender")
	private String gender;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Language> languages;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Experience> experiences;

	@Column(name = "taxt_number")
	private Long taxtNumber;

	@Column(name = "images")
	private String images;

	@Column(name = "candidate_cv")
	private String candidateCV;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "desired_job_id", referencedColumnName = "id")
	private DesiredJob desiredJob;

	@OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Message> candidateMessage;

	@OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Message> recruiterMessage;
	
//	@OneToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "candidate_profile_save_id", referencedColumnName = "id")
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	@JsonIgnore
	private CandidateProfileSaved candidateProfileSaved;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	@JsonIgnore
	private Collection<SavedJob> savedJob;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<AppliedJob> appliedJob;

	public User(Long id, String email) {
		this.id = id;
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", email=");
		builder.append(email);
		builder.append(", address=");
		builder.append(address);
		builder.append(", password=");
		builder.append(password);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
