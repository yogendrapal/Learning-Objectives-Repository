package com.LearningObjectiveRepo.field;

import org.springframework.data.repository.CrudRepository;


public interface FieldRepository extends CrudRepository<Field,Long>{

	public Field findByFieldId(Long fieldId);
	public Field findByFieldName(String fieldName);
}
