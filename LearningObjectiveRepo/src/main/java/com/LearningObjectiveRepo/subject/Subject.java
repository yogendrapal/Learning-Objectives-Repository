package com.LearningObjectiveRepo.subject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.LearningObjectiveRepo.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "subject_id")
	private long subjectId;
	@Column(name = "subject_name",nullable = false)
	private String subjectName;
	@OneToMany(mappedBy="subject")
	private List<Topic> topic = new ArrayList<Topic>();
	public Subject() {
		
	}
	public Subject(String subjectName) {
		super();
		this.subjectName = subjectName;
	}
	public @JsonIgnore List<Topic> getTopic() {
		return topic;
	}
	public void setTopic(List<Topic> topic) {
		this.topic = topic;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
