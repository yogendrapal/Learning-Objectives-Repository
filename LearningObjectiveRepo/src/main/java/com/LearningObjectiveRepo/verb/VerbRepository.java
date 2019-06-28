package com.LearningObjectiveRepo.verb;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VerbRepository extends CrudRepository<Verb,Long>{
	
	public Verb findByVerbId(Long verbId);
	
	public Verb findByVerbName(String verbName);
	
    public List<Verb> findAll();

}
