package com.LearningObjectiveRepo.verb;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.LearningObjectiveRepo.level.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Verb {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="verb_id")
	private Long verbId;
	@Column(name="verb_name")
	private String verbName;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="level_id")
	private Level level;



	public Verb() {
		super();
	}

	public Verb(String verbName) {
		super();
		this.verbName = verbName;
	}

	public Long getVerbId() {
		return verbId;
	}

	public void setVerbId(Long verbId) {
		this.verbId = verbId;
	}

	public String getVerbName() {
		return verbName;
	}

	public void setVerbName(String verbName) {
		this.verbName = verbName;
	}

	public @JsonIgnore Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
