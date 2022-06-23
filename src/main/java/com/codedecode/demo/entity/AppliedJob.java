package com.codedecode.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "AppliedJobs")
public class AppliedJob {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	@ManyToOne
    @JoinColumn(name = "posting_id", referencedColumnName = "id")
    private Posting posting;
	
	@Column(name = "deadline_for_submission")
	private Date deadlineForSubmission;
	
	@Column(name = "date_submission")
	private Date dateSubmission;
	
	@Column(name = "comment_from_employer")
	private String commentFromEmployer;
}
