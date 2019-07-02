package com.LearningObjectiveRepo.level;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.verb.Verb;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Level {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="level_id")
	private Long levelId;
	@Column(name="level_name")
	private String levelName;

	/*
	 * Blob attribute type to accept description in the form of formatted text as well
	 */
	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(length=5000)
	private byte[]  levelDescription =new byte[0];
	

	public Level(String levelName, byte[] levelDescription) {
		super();
		this.levelName = levelName;
		this.levelDescription = levelDescription;
	}

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="taxo_id")
	private Taxonomy taxo;
	
	public byte[] getLevelDescription() {
		return levelDescription;
	}

	public void setLevelDescription(byte[] levelDescription) {
		this.levelDescription = levelDescription;
	}

	@OneToMany(mappedBy="level",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Verb> verb = new ArrayList<>();
	public Level() {
		
	}
	
	public Level(String levelName) {
		super();
		this.levelName = levelName;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public @JsonIgnore  List<Verb> getVerb() {
		return verb;
	}

	public void setVerb(List<Verb> verb) {
		this.verb = verb;
	}

	public @JsonIgnore Taxonomy getTaxo() {
		return taxo;
	}

	public void setTaxo(Taxonomy taxo) {
		this.taxo = taxo;
	}
}
