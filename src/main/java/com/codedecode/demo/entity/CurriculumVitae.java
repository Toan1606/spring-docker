package com.codedecode.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "CurriculumVitaes")
public class CurriculumVitae implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(name = "family_member_id")
	private Long familyMemberId;
	
	@Column(name = "event_id")
	private Long eventId;

	@Column(name = "identity_card_number")
	private String identityCardNumber;
	
	@Column(name = "place_for_identity_cards")
	private String placeForIdentityCards;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "ethnic")
	private String ethnic;
	
	@Column(name = "religion")
	private String religion;
	
	@Column(name = "family_composition_after_land_reform")
	private String familyCompositionAfterLandReform;
	
	@Column(name = "family_member")
	private String familyMember;
	
	@Column(name = "education_level")
	private String educationLevel;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "qualification")
	private String qualification;
	
	@Column(name = "type_of_education")
	private String typeOfEducation;
	
	@Column(name = "specialized_training")
	private String specializedTraining;
	
	@Column(name = "health_situation")
	private String healthSituation;
	
	@Column(name = "tall")
	private String tall;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "occupation_or_qualification")
	private String occupationOrQualification;
	
	@Column(name = "ranks")
	private String rank;
	
	@Column(name = "current_salary")
	private String currentSalary;
	
	@Column(name = "date_of_enlistment")
	private Date dateOfEnlistment;
	
	@Column(name = "date_of_demobilization")
	private Date dateOfDemobilization;
	
	@Column(name = "reason")
	private String reason;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<Event> events;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Collection<FamilyMember> familyMembers;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
}
