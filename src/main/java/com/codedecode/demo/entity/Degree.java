package com.codedecode.demo.entity;

import java.io.Serializable;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Degree")
public class Degree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "certificate_name")
	private String certificateName;

	@Column(name = "teaching_unit")
	private String teachingUnit;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start_time")
	private Date startTime;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_time")
	private Date endTime;

	@Column(name = "major")
	private String major;

	@Column(name = "rank")
	private String rank;

	@Column(name = "supplementary_information")
	private String supplementaryInformation;
}
