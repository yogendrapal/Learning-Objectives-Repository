package com.LearningObjectiveRepo.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.topic.Topic;



@Entity 
//@IdClass(CategoryKey.class)
public class Category {

	@EmbeddedId
	private CategoryId cId;
	
	
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="lo_category",
	           joinColumns= {
	        		   @JoinColumn(name="domainId"),
	        		   @JoinColumn(name="fieldId"),
	        		   @JoinColumn(name="subjectId"),
	        		   @JoinColumn(name="topicId")},inverseJoinColumns = {@JoinColumn(name="lo_id")})
	private Set<LearningObjective> lo = new HashSet<>();
	
	public Category() {
		super();
	}

	public Category(CategoryId cId) {
		super();
		this.cId = cId;
	}

	public CategoryId getcId() {
		return cId;
	}

	public void setcId(CategoryId cId) {
		this.cId = cId;
	}

	public Set<LearningObjective> getLo() {
		return lo;
	}

	public void setLo(Set<LearningObjective> lo) {
		this.lo = lo;
	}


	@ManyToOne @MapsId("domainId")
	@JoinColumn(name="domainId")
	private Domain domain;
	
	@ManyToOne @MapsId("fieldId")
	@JoinColumn(name="fieldId")
	private Field field;
	

	@ManyToOne @MapsId("subjectId")
	@JoinColumn(name="subjectId")
	private Subject subject;
	
	@ManyToOne @MapsId("topicId")
	@JoinColumn(name="topicId")
	private Topic topic;
	

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@ManyToOne @MapsId("subjectId")
	@JoinColumn(name="subjectId")
	private Subject subject;
	
	@ManyToOne @MapsId("topicId")
	@JoinColumn(name="topicId")
	private Topic topic;
}

	
}

