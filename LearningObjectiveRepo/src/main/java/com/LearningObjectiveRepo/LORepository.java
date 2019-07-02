package com.LearningObjectiveRepo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LORepository extends CrudRepository<LearningObjective, Long> {
	
	public LearningObjective findByLObjective(String lObjective);
	
	public LearningObjective findByLoId(Long loId);
	
	
	@Transactional
	@Modifying
	@Query("Delete from LearningObjective where lo_id= :lId")
	public void deleteFromLo(@Param("lId")Long loId);
	
	public List<LearningObjective> findAll();

}
