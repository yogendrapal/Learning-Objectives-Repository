package com.LearningObjectiveRepo.topic;

//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.LearningObjectiveRepo.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private long topicId;
	@Column(name = "topic_name",nullable = false)
	private String topicName;
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="subject")
	private Subject subject;
	
	//@OneToMany(mappedBy="topic")
//	private List<Category> category=new ArrayList<>();
	
	public Topic() {
		
	}
	public Topic(String topicName) {
		super();
		this.topicName = topicName;
	}
	public @JsonIgnore Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public long getTopicId() {
		return topicId;
	}
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
}
