package com.LearningObjectiveRepo.video;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, String> {
	public Video findBySourceId(String sourceId);

	public List<Video> findBySource(String source);
	
	public Video findByVideoId(Long videoId);

}
