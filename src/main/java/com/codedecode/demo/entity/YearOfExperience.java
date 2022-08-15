package com.codedecode.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "YearOfExperience")
public class YearOfExperience implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "yearOfExperience", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	@JsonIgnore
	private List<DesiredJob> desiredJobs;
	
	@OneToMany(mappedBy = "yearOfExperience", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	@JsonIgnore
	private Collection<CV> cvs;
}
