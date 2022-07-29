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
@Table(name = "Languages")
public class Language implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	public Language(Long id, String name, String certificateName, float grade, User user) {
		this.id = id;
		this.name = name;
		this.certificateName = certificateName;
		this.grade = grade;
		this.user = user;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "certificate_name")
	private String certificateName;

	@Column(name = "grade")
	private float grade;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private User user;
}
