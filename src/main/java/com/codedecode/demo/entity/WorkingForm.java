package com.codedecode.demo.entity;

import java.io.Serializable;
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
@Table(name = "WorkingForm")
public class WorkingForm implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "workingForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	@JsonIgnore
	private List<DesiredJob> desiredJobs;

	@OneToMany(mappedBy = "workingForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ToString.Exclude
	@JsonIgnore
	private List<Posting> postings;
}
