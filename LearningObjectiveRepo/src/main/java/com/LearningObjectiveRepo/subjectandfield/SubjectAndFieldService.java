package com.LearningObjectiveRepo.subjectandfield;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.field.FieldRepository;
import com.LearningObjectiveRepo.subject.Subject;
import com.LearningObjectiveRepo.subject.SubjectRepository;

@Service
public class SubjectAndFieldService {
	@Autowired
	private FieldRepository fRepository;

	@Autowired
	private SubjectRepository srepo;

	public void createFieldSub(String field, String sub) {
		Subject s = srepo.findBySubjectName(sub);
		Field f = fRepository.findByFieldName(field);

		// Both video and lo are absent in database
		if (s == null && f == null) {

			Subject sNew = new Subject(sub);
			Field fNew = new Field(field);
			sNew.getField().add(fNew);
			fRepository.save(fNew);
			srepo.save(sNew);

		}

		// Video present but lo is not
		else if (s != null && f == null) {

			Field fNew = new Field(field);
			s.getField().add(fNew);
			fRepository.save(fNew);
			srepo.save(s);
		}

		// Lo is present in database but not the video
		else if (s == null && f != null) {

			Subject sNew = new Subject(sub);
			f.getSubject().add(sNew);
			srepo.save(sNew);
			fRepository.save(f);

		}

	}

	public Boolean createFieldSubById(Long fId, Long sId) {
		Subject s = srepo.findBySubjectId(fId);
		Field f = fRepository.findByFieldId(sId);
		if (s != null && f != null) {
			s.getField().add(f);
			srepo.save(s);
			fRepository.save(f);
			return true;
		} else
			return false;
	}

	public List<Field> readFieldBySubjectId(Long subId) {

		Subject s = srepo.findBySubjectId(subId);
		if (s != null) {
			return s.getField();
		}

		return null;
	}

	public List<Subject> readSubjectByFieldId(Long fId) {
		Field f = fRepository.findByFieldId(fId);
		if (f != null) {
			return f.getSubject();
		}

		return null;
	}

	public Boolean updateFieldBySubject(Subject sub, Long fId) {
		Subject s = srepo.findBySubjectName(sub.getSubjectName());
		Field f = fRepository.findByFieldId(fId);
		if (s == null && f != null) {
			srepo.save(sub);
			f.getSubject().add(sub);
			fRepository.save(f);
			return true;
		} else if (s != null && f != null) {
			f.getSubject().add(s);
			fRepository.save(f);
			return true;
		}
		return false;
	}

	public Boolean updateSubjectByField(Field field, Long sId) {
		Field f = fRepository.findByFieldName(field.getFieldName());
		Subject s = srepo.findBySubjectId(sId);
		if (f == null && s != null) {
			fRepository.save(field);
			s.getField().add(field);
			srepo.save(s);
			return true;
		} else if (s != null && f != null) {
			s.getField().add(f);
			srepo.save(s);
			return true;
		}
		return false;
	}

	public Boolean deleteSubjectByFieldId(Long fId) {
		Field f = fRepository.findByFieldId(fId);
		if (f != null) {
			f.getSubject().clear();
			fRepository.save(f);
			return true;
		}
		return false;
	}

	public Boolean deleteFieldBySubjectId(Long sId) {
		Subject s = srepo.findBySubjectId(sId);
		if (s != null) {
			s.getField().clear();
			srepo.save(s);
			return true;
		}
		return false;
	}

	public Boolean deleteFieldSubjectById(Long sId, Long fId) {
		Field f = fRepository.findByFieldId(fId);
		Subject s = srepo.findBySubjectId(sId);
		if (f != null && s != null) {
			f.getSubject().remove(s);
			s.getField().remove(f);
			fRepository.save(f);
			srepo.save(s);
			return true;
		}

		return false;
	}

}
