package com.LearningObjectiveRepo.topic;

import org.springframework.data.repository.CrudRepository;
public interface TopicRepository extends CrudRepository<Topic,Long> {
	  public Topic findByTopicName(String topicName);
	  public Topic findByTopicId(Long topicId);


}
