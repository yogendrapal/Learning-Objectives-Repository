package com.LearningObjectiveRepo.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.topic.Topic;
import com.LearningObjectiveRepo.topic.TopicRepository;

@Service
public class SubjectService {
	@Autowired
	private SubjectRepository subjectRepo;
	@Autowired
	private TopicRepository topicRepo;

	public void createSubject(Subject subject) {
		Subject s = subjectRepo.findBySubjectName(subject.getSubjectName());
		if (s == null)
			subjectRepo.save(subject);

	}

	public Subject getSubject(Long id) {
		Subject s = subjectRepo.findBySubjectId(id);
		return s;
	}

	public Boolean updateSubjectBySubjectId(Subject sub, Long sId) {
		Subject s = subjectRepo.findBySubjectId(sId);
		Subject subject = subjectRepo.findBySubjectName(sub.getSubjectName());
		if (subject != null)
			return false;
		if (s != null) {
			String subjectName = sub.getSubjectName();
			s.setSubjectName(subjectName);
			subjectRepo.save(s);
		} else {
			subjectRepo.save(sub);
		}
		return true;
	}

	public Subject createSubjectByTopicId(Subject sub, Long tId) {
		Topic t = topicRepo.findByTopicId(tId);
		Subject subject = subjectRepo.findBySubjectName(sub.getSubjectName());
		if (t != null && subject != null) {
			t.setSubject(subject);
			topicRepo.save(t);
			return subject;
		} else if (t != null && subject == null) {
			subjectRepo.save(subject);
			t.setSubject(subject);
			topicRepo.save(t);
			return subject;
		}
		return null;
	}

	public Boolean deleteSubjectBySubjectId(Long sId) {
		Subject s = subjectRepo.findBySubjectId(sId);
		if(s!=null)
		{   for(Topic t : s.getTopic()) {
			t.setSubject(null);
		}
			subjectRepo.delete(s);
			return true;
		}
		return false;
	}

	public Boolean deleteTopicBySubjectId(Long sId) {
		Subject s = subjectRepo.findBySubjectId(sId);
		if(s!=null)
		{  s.getTopic();
		for(Topic t : s.getTopic()) {
			t.setSubject(null);
			topicRepo.save(t);
		}
			
			return true;
		}
		return false;
	}

	public Boolean deleteTopicBySubjectIdAndTopicId(Long sId, Long tId) {
		Subject s = subjectRepo.findBySubjectId(sId);
		Topic t = topicRepo.findByTopicId(tId);
		if(s!=null && t !=null) {
			if(t.getSubject()==s) {
			t.setSubject(null);
			topicRepo.save(t);
			return true;
			}
		}
		return false;
		
	}

	public Subject getSubjectByTopicId(Long tId) {
		Topic t = topicRepo.findByTopicId(tId);
	    return t.getSubject();
	}
	public Boolean updateSubjectByTopicId(Long tId, String subjectName) {
		Topic t=topicRepo.findByTopicId(tId);
		Subject s=subjectRepo.findBySubjectName(subjectName);
		if(t!=null && s!=null)
		{
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		}
		else if(t!=null && s==null)
		{
			s=new Subject(subjectName);
			subjectRepo.save(s);
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		}
			else 
				return false;
		
	}
public Boolean updateTopicBySubjectId(Long tId, Long sId) {
		
		Topic t=topicRepo.findByTopicId(tId);
		Subject s=subjectRepo.findBySubjectId(sId);
		if(t!=null && s!=null)
		{
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		}
		else
			return false;
	}
	

}
