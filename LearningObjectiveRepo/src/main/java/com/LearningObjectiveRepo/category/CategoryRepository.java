package com.LearningObjectiveRepo.category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends CrudRepository<Category, CategoryId> {

	
	
}
