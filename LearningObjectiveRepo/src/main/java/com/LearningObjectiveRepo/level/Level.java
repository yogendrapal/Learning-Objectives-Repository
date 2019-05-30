package com.LearningObjectiveRepo.level;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.verb.Verb;

@Entity
public class Level {
	@Id
	@Column(name="level_id")
	private String levelId;
	@Column(name="level_name")
	private String levelName;
	
	@ManyToOne
	@JoinColumn(name="taxo_id")
	private Taxonomy taxo;
	
	@OneToMany(mappedBy="level")
	private List<Verb> verb = new ArrayList<>();

	

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public List<Verb> getVerb() {
		return verb;
	}

	public void setVerb(List<Verb> verb) {
		this.verb = verb;
	}

	public Taxonomy getTaxo() {
		return taxo;
	}

	public void setTaxo(Taxonomy taxo) {
		this.taxo = taxo;
	}
}
