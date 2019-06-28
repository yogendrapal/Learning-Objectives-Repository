package com.LearningObjectiveRepo.type;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TypeId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long taxoId;
	private Long levelId;
	private Long verbId;

	public Long getTaxoId() {
		return taxoId;
	}

	public void setTaxoId(Long taxoId) {
		this.taxoId = taxoId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Long getVerbId() {
		return verbId;
	}

	public void setVerbId(Long verbId) {
		this.verbId = verbId;
	}

	public TypeId(Long taxoId, Long levelId, Long verbId) {
		super();
		this.taxoId = taxoId;
		this.levelId = levelId;
		this.verbId = verbId;
	}

	public TypeId() {
		super();
	}

}
