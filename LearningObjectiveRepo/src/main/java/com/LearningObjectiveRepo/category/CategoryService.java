package com.LearningObjectiveRepo.category;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.LORepository;
import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.category.CategoryController.LoCategory;
import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.domain.DomainRepository;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.field.FieldRepository;
import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.subject.SubjectRepository;
import com.LearningObjectiveRepo.topic.Topic;
import com.LearningObjectiveRepo.topic.TopicRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository cRepository;

	@Autowired
	private LORepository loRepository;

	@Autowired
	private DomainRepository dRepository;

	@Autowired
	private FieldRepository fRepository;

	@Autowired
	private SubjectRepository sRepository;

	@Autowired
	private TopicRepository tRepository;

	/*
	 * To create category corresponding to the given learning objective
	 */
	public Message createCategory(LoCategory loCategory) {

		Long dId = Long.parseLong(loCategory.getDomainId());
		Long fId = Long.parseLong(loCategory.getFieldId());
		Long sId = Long.parseLong(loCategory.getSubjectId());
		Long tId = Long.parseLong(loCategory.getTopicId());

		CategoryId cId = new CategoryId(dId, fId, sId, tId);
		Optional<Category> c = cRepository.findById(cId);
		LearningObjective lo = loRepository.findByLObjective(loCategory.getLo());

		if (!c.isPresent() && lo == null) {
			Category c1 = new Category(cId);
			lo = new LearningObjective();
			lo.setlObjective(loCategory.getLo());
			Domain d = dRepository.findByDomainId(dId);
			c1.setDomain(d);
			Field f = fRepository.findByFieldId(fId);
			c1.setField(f);
			Subject s = sRepository.findBySubjectId(sId);
			c1.setSubject(s);
			Topic t = tRepository.findByTopicId(tId);
			c1.setTopic(t);

			c1.getcId().setDomainId(dId);
			c1.getcId().setFieldId(fId);
			c1.getcId().setSubjectId(sId);
			c1.getcId().setTopicId(tId);
			lo.getCategory().add(c1);

			loRepository.save(lo);
			cRepository.save(c1);

		}

		else if (lo == null && c.isPresent()) {
			lo = new LearningObjective();
			lo.setlObjective(loCategory.getLo());
			lo.getCategory().add(c.get());
			loRepository.save(lo);
		}

		else if (!c.isPresent() && lo != null) {
			Category c1 = new Category(cId);
			Domain d = dRepository.findByDomainId(dId);
			c1.setDomain(d);
			Field f = fRepository.findByFieldId(fId);
			c1.setField(f);
			Subject s = sRepository.findBySubjectId(sId);
			c1.setSubject(s);
			Topic t = tRepository.findByTopicId(tId);
			c1.setTopic(t);

			c1.getcId().setDomainId(dId);
			c1.getcId().setFieldId(fId);
			c1.getcId().setSubjectId(sId);
			c1.getcId().setTopicId(tId);
			lo.getCategory().add(c1);
			loRepository.save(lo);
			cRepository.save(c1);

		} else {
			lo.getCategory().add(c.get());
			loRepository.save(lo);
		}
		Message m = new Message();
		m.setMessage("Learning objective added successfully.");
		return m;
	}

	
	public Set<Category> readCategoryByLo(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		return lo.getCategory();

	}

}
