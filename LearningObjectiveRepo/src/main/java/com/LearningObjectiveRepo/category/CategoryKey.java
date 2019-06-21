package com.LearningObjectiveRepo.category;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.topic.Topic;
/*
public class CategoryKey implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private CategoryId cId;
	private Long categoryId;

	
}
*/