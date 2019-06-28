package com.LearningObjectiveRepo.subject;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
	
  public Subject findBySubjectName(String sujectName);
  
  public Subject findBySubjectId(Long subjectId);
  
    @Transactional
	@Modifying
	@Query("Delete from Subject where subject_id= :sId")
	public void deleteFromSubject(@Param("sId")Long subjectId);
  
}
