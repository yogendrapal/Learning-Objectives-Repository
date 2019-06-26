package com.LearningObjectiveRepo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;

@RestController
@RequestMapping(value = "/api/subjects")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createSubject(@RequestBody Subject subject) {
		subjectService.createSubject(subject);
		Message m=new Message();
		m.setMessage("Subject submitted successfully");
		return m;
		
	}

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/topics/{topicid}", method = RequestMethod.POST)
	public Message createSubjectByTopicId(@RequestBody Subject subject, @PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Subject s = subjectService.createSubjectByTopicId(subject, tId);
		if (s != null)
			{
			Message m=new Message();
			m.setMessage("Subject submitted successfully");
			return m;

			}
		else
			throw new ResourceNotFoundException("Topic id is not present");
	}

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/{subjectid}", method = RequestMethod.GET)
	public Subject getSubject(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Subject s = subjectService.getSubject(sId);
		if (s != null)
			return s;
		throw new ResourceNotFoundException("Subject is not present for id -" + subjectId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/topics/{topicid}", method = RequestMethod.GET)
	public Subject getSubjectByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Subject s = subjectService.getSubjectByTopicId(tId);
		if (s != null)
			return s;
		throw new ResourceNotFoundException("Subject is not present for id -" + topicId);
	}

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{subjectid}", method = RequestMethod.PUT)
	public Message updateSubjectBySubjectId(@RequestBody Subject sub, @PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.updateSubjectBySubjectId(sub, sId);
		if (b)
			{
			Message m=new Message();
			m.setMessage("Subject updated successfully");
			return m;
			}
		throw new ResourceNotFoundException("Subject having name -  " + sub.getSubjectName() + " already present.");
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/topics/{topicId}", method = RequestMethod.PUT)
	public Message updateSubjectByTopicId(@RequestBody Subject s, @PathVariable("topicId") String tid) {

		Long topicId = Long.parseLong(tid);
		String subjectName = s.getSubjectName();
		Boolean b=subjectService.updateSubjectByTopicId(topicId, subjectName);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Subject for the given topic updated successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Topic id not found - " + topicId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{subjectId}/topics/{topicId}", method = RequestMethod.PUT)
	public Message updateTopicBySubjectId(@PathVariable("subjectId") String sid, @PathVariable("topicId") String tid) {

		Long tId = Long.parseLong(tid);
		Long subId = Long.parseLong(sid);
		Boolean b=subjectService.updateTopicBySubjectId(tId, subId);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Subject for the given topic updated successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{subjectid}", method = RequestMethod.DELETE)
	public Message deleteSubjectBySubjectId(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.deleteSubjectBySubjectId(sId);
		if (b)
		{
			Message m=new Message();
			m.setMessage("Subject deleted successfully");
			return m;
		}	
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{subjectid}/topics", method = RequestMethod.DELETE)
	public Message deleteTopicBySubjectId(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.deleteTopicBySubjectId(sId);
		if (b)
			{
			Message m=new Message();
			m.setMessage("Subject deleted successfully");
			return m;
			}
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{subjectid}/topics/{topicid}", method = RequestMethod.DELETE)
	public Message deleteTopicBySubjectId(@PathVariable("subjectid") String subjectId,@PathVariable("topicid") String topicId) {
		Long sId = Long.parseLong(subjectId);
		Long tId = Long.parseLong(topicId);
		Boolean b = subjectService.deleteTopicBySubjectIdAndTopicId(sId,tId);
		if (b)
			{
			Message m=new Message();
			m.setMessage("Subject deleted successfully");
			return m;
			}
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
		
}
