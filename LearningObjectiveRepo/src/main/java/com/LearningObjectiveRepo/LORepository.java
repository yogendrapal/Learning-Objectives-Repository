package com.LearningObjectiveRepo;

import org.springframework.data.repository.CrudRepository;

public interface LORepository extends CrudRepository<LearningObjective, String> {
	public LearningObjective findByLObjective(String lObjective);
	public LearningObjective findByLoId(Long loId);

}
