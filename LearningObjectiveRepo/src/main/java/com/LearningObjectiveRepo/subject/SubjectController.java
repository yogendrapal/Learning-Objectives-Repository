package com.LearningObjectiveRepo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;

@RestController
@RequestMapping(value = "/api/subjects")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createSubject(@RequestBody Subject subject) {
		subjectService.createSubject(subject);
		return "Subject submitted successfully";
	}

	@RequestMapping(value = "/topics/{topicid}", method = RequestMethod.POST)
	public String createSubjectByTopicId(@RequestBody Subject subject, @PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Subject s = subjectService.createSubjectByTopicId(subject, tId);
		if (s != null)
			return "Subject submitted successfully";
		else
			throw new ResourceNotFoundException("Topic id is not present");
	}

	@RequestMapping(value = "/{subjectid}", method = RequestMethod.GET)
	public Subject getSubject(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Subject s = subjectService.getSubject(sId);
		if (s != null)
			return s;
		throw new ResourceNotFoundException("Subject is not present for id -" + subjectId);
	}
	@RequestMapping(value = "/topics/{topicid}", method = RequestMethod.GET)
	public Subject getSubjectByTopicId(@PathVariable("topicid") String topicId) {
		Long tId = Long.parseLong(topicId);
		Subject s = subjectService.getSubjectByTopicId(tId);
		if (s != null)
			return s;
		throw new ResourceNotFoundException("Subject is not present for id -" + topicId);
	}

	@RequestMapping(value = "/{subjectid}", method = RequestMethod.PUT)
	public String updateSubjectBySubjectId(@RequestBody Subject sub, @PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.updateSubjectBySubjectId(sub, sId);
		if (b)
			return "Udpdated successfully";
		throw new ResourceNotFoundException("Subject having name -  " + sub.getSubjectName() + " already present.");
	}
	@RequestMapping(value = "/topics/{topicId}", method = RequestMethod.PUT)
	public String updateSubjectByTopicId(@RequestBody Subject s, @PathVariable("topicId") String tid) {

		Long topicId = Long.parseLong(tid);
		String subjectName = s.getSubjectName();
		Boolean b=subjectService.updateSubjectByTopicId(topicId, subjectName);
		if(b)
		return "Subject for the given topic updated successfully";
		else
			throw new ResourceNotFoundException("Topic id not found - " + topicId);
	}
	
	@RequestMapping(value = "/{subjectId}/topics/{topicId}", method = RequestMethod.PUT)
	public String updateTopicBySubjectId(@PathVariable("subjectId") String sid, @PathVariable("topicId") String tid) {

		Long tId = Long.parseLong(tid);
		Long subId = Long.parseLong(sid);
		Boolean b=subjectService.updateTopicBySubjectId(tId, subId);
		if(b)
		return "Subject for the given learning objective updated successfully";
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	@RequestMapping(value = "/{subjectid}", method = RequestMethod.DELETE)
	public String deleteSubjectBySubjectId(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.deleteSubjectBySubjectId(sId);
		if (b)
			return "Deleted successfully";
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
	@RequestMapping(value = "/{subjectid}/topics", method = RequestMethod.DELETE)
	public String deleteTopicBySubjectId(@PathVariable("subjectid") String subjectId) {
		Long sId = Long.parseLong(subjectId);
		Boolean b = subjectService.deleteTopicBySubjectId(sId);
		if (b)
			return "Deleted successfully";
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
	@RequestMapping(value = "/{subjectid}/topics/{topicid}", method = RequestMethod.DELETE)
	public String deleteTopicBySubjectId(@PathVariable("subjectid") String subjectId,@PathVariable("topicid") String topicId) {
		Long sId = Long.parseLong(subjectId);
		Long tId = Long.parseLong(topicId);
		Boolean b = subjectService.deleteTopicBySubjectIdAndTopicId(sId,tId);
		if (b)
			return "Deleted successfully";
		throw new ResourceNotFoundException("Subject having id -  " + subjectId + " is not present.");
	}
		
}
