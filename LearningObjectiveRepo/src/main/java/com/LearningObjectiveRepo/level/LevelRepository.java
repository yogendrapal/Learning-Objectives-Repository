package com.LearningObjectiveRepo.level;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface LevelRepository extends CrudRepository<Level, Long> {
	public Level findByLevelName(String levelName);
	public Level findByLevelId(Long lId);
	public List<Level> findAll();
}
