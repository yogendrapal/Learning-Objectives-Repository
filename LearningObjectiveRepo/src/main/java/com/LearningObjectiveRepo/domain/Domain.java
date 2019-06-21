package com.LearningObjectiveRepo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//import com.LearningObjectiveRepo.category.Category;
import com.LearningObjectiveRepo.field.Field;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Domain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "domain_id")
	private long domainId;
	
	@Column(name = "domain_name")
	private String domainName;
	
	@OneToMany(mappedBy="domain",cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Field> field=new ArrayList<Field>();
	
//	@OneToMany(mappedBy="domain")
	//private List<Category> category=new ArrayList<>();

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public @JsonIgnore List<Field> getField() {
		return field;
	}

	public Domain() {
		super();
	}

	public Domain(String domainName) {
		super();
		this.domainName = domainName;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}

}
