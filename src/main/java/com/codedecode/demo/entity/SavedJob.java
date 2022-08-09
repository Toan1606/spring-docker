package com.codedecode.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.codedecode.demo.entity.key.SavedJobKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SavedJobs")
public class SavedJob {

	@EmbeddedId
	private SavedJobKey savedJobKey;

	@ManyToOne
	@MapsId(value="userId")
	@JoinColumn(name = "candidate_id", referencedColumnName = "id")
	@JsonIgnore
	private User user;

	@ManyToOne
	@MapsId(value="postingId")
	@JoinColumn(name = "posting_id", referencedColumnName = "id")
	@ToString.Exclude
	@JsonIgnore
	private Posting posting;

	@Temporal(TemporalType.DATE)
	@Column(name = "saved_date")
	private Date savedDate;
}
