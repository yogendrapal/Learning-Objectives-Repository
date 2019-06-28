package com.LearningObjectiveRepo.field;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface FieldRepository extends CrudRepository<Field,Long>{

	public Field findByFieldId(Long fieldId);
	
	public Field findByFieldName(String fieldName);
	
	@Transactional
	@Modifying
	@Query("Delete from Field where field_id= :fId")
	public void deleteFromField(@Param("fId")Long fieldId);
}
