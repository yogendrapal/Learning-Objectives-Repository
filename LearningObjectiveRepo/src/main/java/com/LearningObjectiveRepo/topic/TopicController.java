package com.LearningObjectiveRepo.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.subject.Subject;
@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {
	@Autowired 
	private TopicService topicService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createTopic(@RequestBody Topic topic) {
		topicService.createTopic(topic);
		return "Topic submitted successfully";
	}
	@RequestMapping(value = "/subjects/{subjectid}", method = RequestMethod.POST)
	public String createTopicBySubjectId(@RequestBody Topic topic,@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
	     Subject s = topicService.createTopicBySubjectId(topic,sId);
		if(s!=null)
		return "Topic submitted successfully";
		else throw new ResourceNotFoundException("Subject id is not present");
	}
	@RequestMapping(value = "/{topicid}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Topic t = topicService.getTopic(tId);
		if(t!=null)
			return t;
		throw new ResourceNotFoundException("Topic is not present for id -"+ topicId);
	}
	@RequestMapping(value = "subjects/{subid}", method = RequestMethod.GET)
	public List<Topic> getTopicBySubjectId(@PathVariable("subid")String subId) {
		Long sId = Long.parseLong(subId);
		List<Topic> topic = topicService.getTopicBySubjectId(sId);
		if(topic == null) {
			throw new ResourceNotFoundException("Subject id not found - " + subId);
		}
		else if(topic.isEmpty())
			throw new ResourceNotFoundException("List is Empty");
		return topic;
	}
	@RequestMapping(value = "/{topicid}", method = RequestMethod.PUT)
	public String updateTopicByTopicId(@RequestBody Topic topic,@PathVariable("topicid")String topicId) {
		Long tId = Long.parseLong(topicId);
	     Boolean b = topicService.updateTopicByTopicId(topic,tId);
	     if(b)
		return "Udpdated successfully";
	     throw new ResourceNotFoundException("Topic having name -  "+topic.getTopicName()+" already present." );
	}
	@RequestMapping(value = "/{topicid}/subjects/{subjectid}", method = RequestMethod.PUT)
	public String updateTopicBySubjectId(@PathVariable("topicid")String topicId,@PathVariable("subjectid")String subjectId) {
		Long tId = Long.parseLong(topicId);
		Long sId = Long.parseLong(subjectId);
         Boolean b = topicService.updateTopicBySubjectId(tId,sId);
         if(b)
		return "Udpdated successfully";
 		throw new ResourceNotFoundException("Udpdation not possible");
	}
	@RequestMapping(value = "/subjects/{subjectId}", method = RequestMethod.PUT)
	public String updateTopicBySubjectId(@RequestBody Topic t, @PathVariable("subjectId") String sid) {

		Long subId = Long.parseLong(sid);
		String topicName = t.getTopicName();
		Boolean b=topicService.updateTopicBySubjectId(subId, topicName);
		if(b)
		return "Topic for the given subject updated successfully";
		else
			throw new ResourceNotFoundException("Subject id not found - " + subId);
	}
	
	@RequestMapping(value = "/{topicid}", method = RequestMethod.DELETE)
	public String deleteTopicByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Boolean b = topicService.deleteTopicByTopicId(tId);
		if (b)
			return "Deleted successfully";
		throw new ResourceNotFoundException("Topic having id -  " + topicId + " is not present.");
	}
	@RequestMapping(value = "/{topicid}/subjects", method = RequestMethod.DELETE)
	public String deleteSubjectByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Boolean b = topicService.deleteSubjectByTopicId(tId);
		if (b)
			return "Deleted successfully";
		throw new ResourceNotFoundException("Topic having id -  " + topicId + " is not present.");
	}
}
