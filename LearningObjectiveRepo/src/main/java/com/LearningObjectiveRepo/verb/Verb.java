package com.LearningObjectiveRepo.verb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.LearningObjectiveRepo.level.Level;

@Entity
public class Verb {
	@Id
	@Column(name="verb_id")
	private String verbId;
	@Column(name="verb_name")
	private String verbName;
	
	@ManyToOne 
	@JoinColumn(name="level_id")
	private Level level;

	public String getVerbId() {
		return verbId;
	}

	public void setVerbId(String verbId) {
		this.verbId = verbId;
	}

	public String getVerbName() {
		return verbName;
	}

	public void setVerbName(String verbName) {
		this.verbName = verbName;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
}
