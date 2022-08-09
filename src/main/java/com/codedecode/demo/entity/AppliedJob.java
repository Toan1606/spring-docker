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

import com.codedecode.demo.entity.key.AppliedJobKey;
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
@Table(name = "AppliedJobs")
public class AppliedJob {

	@EmbeddedId
	private AppliedJobKey appliedJobKey;


	@ManyToOne
    @MapsId(value = "candidateId")
	@JoinColumn(name = "candidate_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
    private User candidate;
	
	@ManyToOne
    @MapsId(value = "recruiterId")
	@JoinColumn(name = "recruiter_id", referencedColumnName = "id")

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
    private User recruiter;
	
	@ManyToOne
	@MapsId(value = "postingId")
	@JoinColumn(name = "posting_id", referencedColumnName = "id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonIgnore
    private Posting posting;
	
	@Column(name = "deadline_for_submission")
	private Date deadlineForSubmission;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "date_submission")
	private Date dateSubmission;
	
	@Column(name = "comment_from_employer")
	private String commentFromEmployer;
}
