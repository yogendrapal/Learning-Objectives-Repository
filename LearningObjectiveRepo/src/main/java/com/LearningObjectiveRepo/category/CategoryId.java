
package com.LearningObjectiveRepo.category;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CategoryId implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long domainId;
	private Long fieldId;
	private Long subjectId;
	private Long topicId;

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}

	public CategoryId(Long domainId, Long fieldId, Long subjectId, Long topicId) {
		super();
		this.domainId = domainId;
		this.fieldId = fieldId;
		this.subjectId = subjectId;
		this.topicId = topicId;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public CategoryId() {
		super();
	}

}
