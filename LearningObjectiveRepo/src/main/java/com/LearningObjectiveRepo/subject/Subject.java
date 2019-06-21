package com.LearningObjectiveRepo.subject;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.LearningObjectiveRepo.field.Field;
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
	public List<Field> getField() {
		return field;
	}
	public void setField(List<Field> field) {
		this.field = field;
	}
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="subject_field",joinColumns= {@JoinColumn(name="subject_id")},inverseJoinColumns = {@JoinColumn(name="field_id")})
	private List<Field> field = new ArrayList<Field>();

	//@OneToMany(mappedBy="subject")
	//private List<Category> category=new ArrayList<>();
	
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
