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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_contract")
public class UserContract implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "candidateContract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<User> candidates;
	
	@OneToMany(mappedBy = "recruiterContract", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Collection<User> recruiter;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "contract_id", referencedColumnName = "id")
	private Contract contract;
	
	@Column(name = "status")
	private boolean status;
	
	
}
