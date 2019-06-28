package com.LearningObjectiveRepo.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.subject.SubjectRepository;

@Service
public class TopicService {
	@Autowired
	private TopicRepository topicRepo;
	@Autowired
	private SubjectRepository subjectRepo;

	public void createTopic(Topic topic) {
		Topic t = topicRepo.findByTopicName(topic.getTopicName());
		if (t == null)
			topicRepo.save(topic);
	}

	public Topic getTopic(Long id) {
		Topic t = topicRepo.findByTopicId(id);
		return t;
	}

	public List<Topic> getTopicBySubjectId(Long sId) {
		Subject s = subjectRepo.findBySubjectId(sId);
		List<Topic> topic = s.getTopic();
		if (topic == null)
			return null;
		return topic;
	}

	public Boolean updateTopicByTopicId(Topic top, Long tId) {
		Topic t = topicRepo.findByTopicId(tId);
		Topic topic = topicRepo.findByTopicName(top.getTopicName());
		if (topic != null)
			return false;
		if (t != null) {
			String topicName = top.getTopicName();
			t.setTopicName(topicName);
			topicRepo.save(t);
		} else {
			topicRepo.save(top);
		}
		return true;
	}

	public Boolean updateTopicBySubjectId(Long topicId, Long subId) {
		Topic t = topicRepo.findByTopicId(topicId);
		Subject s = subjectRepo.findBySubjectId(subId);
		if (t != null && s != null) {
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		}
		return false;
	}

	public Boolean updateTopicBySubjectId(Long sId, String topicName) {
		Subject s = subjectRepo.findBySubjectId(sId);
		Topic t = topicRepo.findByTopicName(topicName);
		if (s != null && t != null) {
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		} else if (s != null && t == null) {
			t = new Topic(topicName);
			t.setSubject(s);
			topicRepo.save(t);
			return true;
		} else
			return false;

	}

	public Subject createTopicBySubjectId(Topic top, Long subId) {
		Subject sub = subjectRepo.findBySubjectId(subId);
		Topic topic = topicRepo.findByTopicName(top.getTopicName());
		if (sub != null && topic == null) {
			top.setSubject(sub);
			topicRepo.save(top);
			return sub;
		} else if (sub != null && topic != null) {
			topic.setSubject(sub);
			topicRepo.save(topic);
			return sub;
		}
		return null;
	}

	public Boolean deleteTopicByTopicId(Long tId) {

		Topic t = topicRepo.findByTopicId(tId);
		if (t != null) {
			topicRepo.delete(t);
			return true;
		}
		return false;
	}

	public Boolean deleteSubjectByTopicId(Long tId) {
		Topic t = topicRepo.findByTopicId(tId);
		if (t != null) {
			t.setSubject(null);
			topicRepo.save(t);
			return true;
		}
		return false;
	}
}
