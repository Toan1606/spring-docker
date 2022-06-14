package com.codedecode.demo.entity.many_to_many;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Entity
@AllArgsConstructor
@Table(name = "viewers")
public class Viewer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "stream_view", joinColumns = @JoinColumn(name = "viewer_id"), inverseJoinColumns = @JoinColumn(name = "streamer_id"))
	private Set<Streamer> streamers;
}
