package com.LearningObjectiveRepo.field;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.domain.Domain;
import com.LearningObjectiveRepo.domain.DomainRepository;

@Service
public class FieldService {

	@Autowired
	private FieldRepository fRepository;
	@Autowired
	private DomainRepository dRepository;

	public Boolean createField(Field field) {
		Field f = fRepository.findByFieldName(field.getFieldName());
		if (f == null) {
			fRepository.save(field);
			return true;
		}
		return false;

	}

	public Field readFieldByFieldId(Long fieldId) {
		Field f = fRepository.findByFieldId(fieldId);
		if (f != null) {
			return f;
		}
		return null;
	}

	public Boolean updateFieldByFieldId(Field field, Long fieldId) {
		Field f1 = fRepository.findByFieldName(field.getFieldName());
		Field f2 = fRepository.findByFieldId(fieldId);
		if (f1 != null) {
			return false;
		} else if (f2 != null) {
			String fName = field.getFieldName();
			f2.setFieldName(fName);
			fRepository.save(f2);
		} else {
			fRepository.save(field);
		}
		return true;
	}

	public Boolean deleteFieldByFieldId(Long fieldId) {
		Field f = fRepository.findByFieldId(fieldId);
		if (f != null) {

			f.setDomain(null);
			f.getSubject().clear();
			fRepository.deleteFromField(fieldId);
			// fRepository.delete(f);
			return true;
		}

		return false;

	}

	public Domain createFieldByDomain(Long domainId, Field field) {
		Domain d = dRepository.findByDomainId(domainId);
		Field f = fRepository.findByFieldName(field.getFieldName());
		if (d != null && f == null) {
			field.setDomain(d);
			fRepository.save(field);
			return d;
		} else if (d != null && f != null) {
			f.setDomain(d);
			fRepository.save(f);
			return d;
		}
		return null;
	}

	public List<Field> getFieldByDomainId(Long domainId) {
		Domain d = dRepository.findByDomainId(domainId);
		List<Field> f = d.getField();
		return f;
	}

	public Boolean updateFieldByDomainId(Long domainId, Field field) {
		Domain d = dRepository.findByDomainId(domainId);
		Field f = fRepository.findByFieldName(field.getFieldName());
		if (f != null && d != null) {
			f.setDomain(d);
			fRepository.save(f);
			return true;
		} else if (f == null && d != null) {

			field.setDomain(d);
			fRepository.save(field);
			return true;
		} else
			return false;
	}

	public Boolean updateFieldByDomainId(Long fieldId, Long domainId) {
		Domain d = dRepository.findByDomainId(domainId);
		Field f = fRepository.findByFieldId(fieldId);
		if (d != null && f != null) {
			f.setDomain(d);
			fRepository.save(f);
			return true;
		}
		return false;
	}

	public Boolean deleteFieldByDomainId(Long domainId) {
		Domain d = dRepository.findByDomainId(domainId);
		if (d != null) {

			for (Field f : d.getField()) {
				f.setDomain(null);
				fRepository.save(f);
			}
			return true;
		}
		return false;

	}

}
