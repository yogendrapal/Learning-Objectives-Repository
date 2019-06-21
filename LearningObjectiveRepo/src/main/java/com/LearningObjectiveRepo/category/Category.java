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
/*
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.topic.Topic;

@Entity
public class Category {

	@EmbeddedId private CategoryId categoryid;
	

	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="lo_category",joinColumns= {@JoinColumn(name="categoryId")},inverseJoinColumns = {@JoinColumn(name="lo_id")})
	private List<LearningObjective> lo = new ArrayList<LearningObjective>();
	
	@ManyToOne @MapsId("domainId")
	@JoinColumn(name="domainId")
	private Domain domain;
	
	@ManyToOne @MapsId("fieldId")
	@JoinColumn(name="fieldId")
	private Field field;
	
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
*/