package com.LearningObjectiveRepo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LORepository extends CrudRepository<LearningObjective, String> {
	public LearningObjective findByLObjective(String lObjective);
	public LearningObjective findByLoId(Long loId);
	
	/*@Query("Delete from video_lo where lo_lo_id= :lId")
	public void deleteFromVideo_Lo(@Param("lId") Long loId);*/
	
	@Transactional
	@Modifying
	@Query("Delete from LearningObjective where lo_id= :lId")
	public void deleteFromLo(@Param("lId")Long loId);

}
