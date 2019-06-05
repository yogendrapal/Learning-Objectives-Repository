package com.LearningObjectiveRepo.taxonomy;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.level.Level;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Taxonomy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="taxo_id")
	private Long taxoId;
	@Column(name="taxo_name")
	private String taxoName;
	
	@OneToMany(mappedBy="taxonomy")
	private List<LearningObjective> lo=new ArrayList<>();
	
	@OneToMany(mappedBy="taxo")
	private List<Level> level = new ArrayList<>();



	public Taxonomy() {
		super();
	}

	public Taxonomy(String taxoName) {
		super();
		this.taxoName = taxoName;
	}

	public Long getTaxoId() {
		return taxoId;
	}

	public void setTaxoId(Long taxoId) {
		this.taxoId = taxoId;
	}

	public String getTaxoName() {
		return taxoName;
	}

	public void setTaxoName(String taxoName) {
		this.taxoName = taxoName;
	}

	public @JsonIgnore List<LearningObjective> getLo() {
		return lo;
	}

	public void setLo(List<LearningObjective> lo) {
		this.lo = lo;
	}

	
	
	
}
