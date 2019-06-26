package com.LearningObjectiveRepo.field;

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
import javax.persistence.ManyToOne;

import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "field_id")
	protected long fieldId;
	
	
	
	@Column(name = "field_name")
	private String fieldName;
	
	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="domain_id")
	private Domain domain;

	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="subject_field",joinColumns= {@JoinColumn(name="field_id")},inverseJoinColumns = {@JoinColumn(name="subject_id")})
	private List<Subject> subject = new ArrayList<Subject>();

	//@OneToMany(mappedBy="field")
	//private List<Category> category=new ArrayList<>();
	
	public long getFieldId() {
		return fieldId;
	}

	public @JsonIgnore List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public void setFieldId(long fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public @JsonIgnore Domain getDomain() {
		return domain;
	}

	public Field() {
		super();
	}

	public Field(String fieldName) {
		super();
		this.fieldName = fieldName;
		
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}
	
}
