package com.LearningObjectiveRepo.field;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.domain.Domain;


@RestController
@RequestMapping(value="/api/fields")
public class FieldController {

	@Autowired
	private FieldService fService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createField(@RequestBody Field field) {
		Boolean b=fService.createField(field);
		if(b)
		return "Field submitted successfully";
		else
			return "Field Name already exists.";
	}
	
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.GET)
	public Field readFieldByFieldId(@PathVariable ("fieldId") String fId) {
		Long fieldId=Long.parseLong(fId);
		Field f=fService.readFieldByFieldId(fieldId);
		if(f==null)
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
		return f;
	}
	
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.PUT)
	public String updateFieldByFieldId(@RequestBody Field field,@PathVariable ("fieldId") String fId) {
		Long fieldId=Long.parseLong(fId);
		Boolean b=fService.updateFieldByFieldId(field,fieldId);
		if(b)
			return "Udpdated successfully";
        throw new ResourceNotFoundException("Field having name -  "+field.getFieldName()+" already present." );
	}
	
	@RequestMapping(value = "/{fieldId}", method = RequestMethod.DELETE)
	public String deleteFieldByFieldId(@PathVariable("fieldId")String fId) {
		Long fieldId = Long.parseLong(fId);
         Boolean b = fService.deleteFieldByFieldId(fieldId);
        if(b)
		return "Deleted successfully";
        throw new ResourceNotFoundException("Field Id is not valid");
	}
	
	@RequestMapping(value = "/domains/{domainId}", method = RequestMethod.POST)
	public String createFieldByDomain(@PathVariable("domainId") String dId ,@RequestBody Field field) {
		Long domainId = Long.parseLong(dId);
		Domain d = fService.createFieldByDomain(domainId,field);
		if(d!=null)
		return "Field submitted successfully";
		else throw new ResourceNotFoundException("Domain id is not present");
	}
	
	@RequestMapping(value = "domains/{domainId}", method = RequestMethod.GET)
	public List<Field> getFieldByDomainId(@PathVariable("domainId")String dId) {
		Long domainId = Long.parseLong(dId);
		List<Field> f = fService.getFieldByDomainId(domainId);
		if(f == null) {
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
		}
		else if(f.isEmpty())
			throw new ResourceNotFoundException("List is Empty");
		return f;
	}
	
	@RequestMapping(value = "/domains/{domainId}", method = RequestMethod.PUT)
	public String updateFieldByDomainId(@RequestBody Field field, @PathVariable("domainId") String did) {

		Long domainId = Long.parseLong(did);
		
		Boolean b=fService.updateFieldByDomainId(domainId, field);
		if(b)
		return "Field for the given domain updated successfully";
		else
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
	}
	
	@RequestMapping(value = "/{fieldId}/domains/{domainId}", method = RequestMethod.PUT)
	public String updateFieldByDomainId(@PathVariable("fieldId")String fId,@PathVariable("domainId")String dId) {
		Long fieldId = Long.parseLong(fId);
		Long domainId = Long.parseLong(dId);
         Boolean b = fService.updateFieldByDomainId(fieldId,domainId);
         if(b)
		return "Udpdated successfully";
 		throw new ResourceNotFoundException("Udpdation not possible");
	}
	
	@RequestMapping(value = "domains/{domainId}", method = RequestMethod.DELETE)
	public String deleteFieldByDomainId(@PathVariable("domainId")String dId) {
		Long domainId = Long.parseLong(dId);
         Boolean b = fService.deleteFieldByDomainId(domainId);
        if(b)
		return "Deleted successfully";
        throw new  ResourceNotFoundException("Domain Id is not valid");
	}
	
}
