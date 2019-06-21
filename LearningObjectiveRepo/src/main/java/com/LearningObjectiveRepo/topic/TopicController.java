package com.LearningObjectiveRepo.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.subject.Subject;
@RestController
@RequestMapping(value = "/api/secured/topics")
public class TopicController {
	@Autowired 
	private TopicService topicService;

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createTopic(@RequestBody Topic topic) {
		topicService.createTopic(topic);
		Message m=new Message();
		m.setMessage("Topic submitted successfully");
		return m;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/subjects/{subjectid}", method = RequestMethod.POST)
	public Message createTopicBySubjectId(@RequestBody Topic topic,@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
	     Subject s = topicService.createTopicBySubjectId(topic,sId);
		if(s!=null)
		{
			Message m=new Message();
			m.setMessage("Topic submitted successfully");
			return m;
		}
		else throw new ResourceNotFoundException("Subject id is not present");
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/{topicid}", method = RequestMethod.GET)
	public Topic getTopic(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Topic t = topicService.getTopic(tId);
		if(t!=null)
			return t;
		throw new ResourceNotFoundException("Topic is not present for id -"+ topicId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
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
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{topicid}", method = RequestMethod.PUT)
	public Message updateTopicByTopicId(@RequestBody Topic topic,@PathVariable("topicid")String topicId) {
		Long tId = Long.parseLong(topicId);
	     Boolean b = topicService.updateTopicByTopicId(topic,tId);
	     if(b)
		  {
	    	 Message m=new Message();
	 		m.setMessage("Topic updated successfully");
	 		return m;
		  }
	     throw new ResourceNotFoundException("Topic having name -  "+topic.getTopicName()+" already present." );
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{topicid}/subjects/{subjectid}", method = RequestMethod.PUT)
	public Message updateTopicBySubjectId(@PathVariable("topicid")String topicId,@PathVariable("subjectid")String subjectId) {
		Long tId = Long.parseLong(topicId);
		Long sId = Long.parseLong(subjectId);
         Boolean b = topicService.updateTopicBySubjectId(tId,sId);
         if(b)
		{
        	 Message m=new Message();
     		m.setMessage("Topic updated successfully");
     		return m;
		}
 		throw new ResourceNotFoundException("Udpdation not possible");
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/subjects/{subjectId}", method = RequestMethod.PUT)
	public Message updateTopicBySubjectId(@RequestBody Topic t, @PathVariable("subjectId") String sid) {

		Long subId = Long.parseLong(sid);
		String topicName = t.getTopicName();
		Boolean b=topicService.updateTopicBySubjectId(subId, topicName);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Topic for the given subject updated successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Subject id not found - " + subId);
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{topicid}", method = RequestMethod.DELETE)
	public Message deleteTopicByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Boolean b = topicService.deleteTopicByTopicId(tId);
		if (b)
			{
			 Message m=new Message();
	     		m.setMessage("Topic deleted successfully");
	     		return m;
			}
		throw new ResourceNotFoundException("Topic having id -  " + topicId + " is not present.");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{topicid}/subjects", method = RequestMethod.DELETE)
	public Message deleteSubjectByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Boolean b = topicService.deleteSubjectByTopicId(tId);
		if (b)
			{
			 Message m=new Message();
	     		m.setMessage("Topic deleted successfully");
	     		return m;
			}
		throw new ResourceNotFoundException("Topic having id -  " + topicId + " is not present.");
	}
}
