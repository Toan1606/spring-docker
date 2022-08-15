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

import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@Indexed
@Entity
@Table(name = "posting_category")
public class PostingCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "category_name")
	private String categoryName;
	
	@OneToMany(mappedBy = "postingCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<Posting> postings;
	
	@OneToMany(mappedBy = "postingCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	@ToString.Exclude
	private List<DesiredJob> desiredJobs;
}
