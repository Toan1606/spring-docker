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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "Address")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Province> provinces;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<City> cities;
	
	@OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
	private Collection<Street> streets;
	
	@OneToOne(mappedBy = "address")
	private Posting posting;
	
	@OneToOne(mappedBy = "address")
	private User user;
}
