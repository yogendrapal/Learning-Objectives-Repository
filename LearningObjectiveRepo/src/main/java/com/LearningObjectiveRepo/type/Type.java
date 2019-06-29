package com.LearningObjectiveRepo.type;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.level.Level;
import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.verb.Verb;

@Entity
public class Type {
	
	@EmbeddedId
	private TypeId tId;
	
	@ManyToOne @MapsId("taxoId")
	@JoinColumn(name="taxoId")
	private Taxonomy taxonomy;
	
	@ManyToOne @MapsId("levelId")
	@JoinColumn(name="levelId")
	private Level level;
	
	@ManyToOne @MapsId("verbId")
	@JoinColumn(name="verbId")
	private Verb verb;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="lo_type",
	           joinColumns= {
	        		  
	        		   @JoinColumn(name="levelId"),
	        		   @JoinColumn(name="taxoId"),
	        		   @JoinColumn(name="verbId")},inverseJoinColumns = {@JoinColumn(name="lo_id")})
	private Set<LearningObjective> lo = new HashSet<>();

	public TypeId gettId() {
		return tId;
	}

	public void settId(TypeId tId) {
		this.tId = tId;
	}

	public Taxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Verb getVerb() {
		return verb;
	}

	public void setVerb(Verb verb) {
		this.verb = verb;
	}

	public Set<LearningObjective> getLo() {
		return lo;
	}

	public void setLo(Set<LearningObjective> lo) {
		this.lo = lo;
	}

	public Type(TypeId tId) {
		super();
		this.tId = tId;
	}

	public Type() {
		super();
	}
	
	

}
