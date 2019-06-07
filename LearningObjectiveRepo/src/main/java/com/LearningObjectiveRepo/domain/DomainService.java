package com.LearningObjectiveRepo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LearningObjectiveRepo.field.Field;
import com.LearningObjectiveRepo.field.FieldRepository;

@Service
public class DomainService {

	@Autowired
	private DomainRepository dRepository;
	
	@Autowired
	private FieldRepository fRepository;
	
	
	public Boolean createDomain(Domain domain) {
		Domain d = dRepository.findByDomainName(domain.getDomainName());
		if(d==null) {
		dRepository.save(domain);
		return true;
		}
		return false;
		
	}


	public Domain readDomainByDomainId(Long domainId) {
		Domain d=dRepository.findByDomainId(domainId);
		if(d!=null)
		{
			return d;
		}
		return null;
	}


	public Boolean updateDomainByDomainId(Domain domain, Long domainId) {
		Domain d1 = dRepository.findByDomainName(domain.getDomainName());
		Domain d2=dRepository.findByDomainId(domainId);
		if(d1!=null )
		{
			return false;
		}
		else if(d2!=null)
		{
			String dName = domain.getDomainName();
			d2.setDomainName(dName);
			dRepository.save(d2);
			}else {
				dRepository.save(domain);
			}
			return true;
	}


	public Boolean deleteDomainByDomainId(Long domainId) {
		Domain d = dRepository.findByDomainId(domainId);
		if(d!=null) {
			
			for(Field f : d.getField() ) {
				f.setDomain(null);
				fRepository.save(f);
				}
			d.getField().clear();
			dRepository.delete(d);
			return true;
		}
			
		return false;
		
	}


	public Boolean createDomainByFieldId(Domain domain, Long fieldId) {
		Field f=fRepository.findByFieldId(fieldId);
		Domain d=dRepository.findByDomainName(domain.getDomainName());
		if(f!=null && d!=null)
		{
			f.setDomain(d);
			fRepository.save(f);
			return true;
		}
		else if(f!=null && d==null)
		{
		
		dRepository.save(domain);
		f.setDomain(domain);
		fRepository.save(f);
		return true;
		}
		else 
			return false;
	}


	public List<Field> readFieldsByDomainId(Long domainId) {
		Domain d=dRepository.findByDomainId(domainId);
		if(d!=null)
		{
			return d.getField();
		}
		return null;
	}


	public Domain readDomainByFieldId(Long fieldId) {
		Field f=fRepository.findByFieldId(fieldId);
		if(f!=null)
			return f.getDomain();
		return null;
	}


	public Boolean updateDomainByFieldId(Long fieldId, Domain domain) {
		Field f=fRepository.findByFieldId(fieldId);
		Domain d=dRepository.findByDomainName(domain.getDomainName());
		if(f!=null && d!=null)
		{
			f.setDomain(d);
			fRepository.save(f);
			return true;
		}
		else if(f!=null && d==null)
		{
			
			
			f.setDomain(domain);
			dRepository.save(domain);
			return true;
		}
			else 
				return false;
	}


	public Boolean updateFieldByDomainId(Long domainId, Long fieldId) {
		Field f=fRepository.findByFieldId(fieldId);
		Domain d=dRepository.findByDomainId(domainId);
		if(f!=null && d!=null)
		{
			f.setDomain(d);
			fRepository.save(f);
			return true;
		}
		else
			return false;
	}


	public Boolean deleteDomainByFieldId(Long fieldId) {
		Field f=fRepository.findByFieldId(fieldId);
		if(f!=null)
		{
			f.setDomain(null);
			fRepository.save(f);
			return true;
		}
		return false;
	}
	

}
