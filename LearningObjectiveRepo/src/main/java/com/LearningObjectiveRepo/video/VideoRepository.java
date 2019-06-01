package com.LearningObjectiveRepo.video;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface VideoRepository extends CrudRepository<Video, String> {
	public Video findBySourceId(String sourceId);

	public List<Video> findBySource(String source);
	
	public Video findByVideoId(Long videoId);
	
	@Transactional
	@Modifying
	@Query("Delete from Video where video_id= :vId")
	public void deleteFromVideo(@Param("vId")Long videoId);

}
