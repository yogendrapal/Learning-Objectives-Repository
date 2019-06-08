package com.LearningObjectiveRepo.subject;

import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
  public Subject findBySubjectName(String sujectName);
  public Subject findBySubjectId(Long subjectId);
  

  
}
