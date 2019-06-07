package com.LearningObjectiveRepo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.field.Field;


@RestController
@RequestMapping(value="/api/domains")
public class DomainController {
	
	@Autowired
	private DomainService dService;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createDomain(@RequestBody Domain domain) {
		Boolean b=dService.createDomain(domain);
		if(b)
		return "Domain submitted successfully";
		else
			return "Domain Name already exists.";
	}
	
	@RequestMapping(value = "/{domainId}", method = RequestMethod.GET)
	public Domain readDomainByDomainId(@PathVariable ("domainId") String dId) {
		Long domainId=Long.parseLong(dId);
		Domain d=dService.readDomainByDomainId(domainId);
		if(d==null)
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
		return d;
	}
	
	@RequestMapping(value = "/{domainId}", method = RequestMethod.PUT)
	public String updateDomainByDomainId(@RequestBody Domain domain,@PathVariable ("domainId") String dId) {
		Long domainId=Long.parseLong(dId);
		Boolean b=dService.updateDomainByDomainId(domain,domainId);
		if(b)
			return "Udpdated successfully";
        throw new ResourceNotFoundException("Domain having name -  "+domain.getDomainName()+" already present." );
	}
	
	@RequestMapping(value = "/{domainId}", method = RequestMethod.DELETE)
	public String deleteDomainByDomainId(@PathVariable("domainId")String dId) {
		Long domainId = Long.parseLong(dId);
         Boolean b = dService.deleteDomainByDomainId(domainId);
        if(b)
		return "Deleted successfully";
        throw new ResourceNotFoundException("Domain Id is not valid");
	}
	
	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.POST)
	public String createDomainByFieldId(@RequestBody Domain domain, @PathVariable ("fieldId") String fid)
	{
		Long fieldId=Long.parseLong(fid);
		Boolean b=dService.createDomainByFieldId(domain,fieldId);
		if(b)
		return "Domain corresponding to the given field added successfully";
		else
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
	}
	
	@RequestMapping(value="/{domainId}/fields",method=RequestMethod.GET)
	public List<Field> readFieldsByDomainId(@PathVariable ("domainId") String dId)
	{
		Long domainId = Long.parseLong(dId);
		List<Field> f = dService.readFieldsByDomainId(domainId);
		if (f == null || f.isEmpty())
			throw new ResourceNotFoundException("No fields with domain id - " + domainId);
		return f;
	}
	
	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.GET)
	public Domain readDomainByFieldId(@PathVariable ("fieldId") String fId)
	{
		Long fieldId = Long.parseLong(fId);
		Domain d = dService.readDomainByFieldId(fieldId);
		if (d == null )
			throw new ResourceNotFoundException("Field id not related to any domain - " + fieldId);
		return d;
	}
	
	@RequestMapping(value = "/fields/{fieldId}", method = RequestMethod.PUT)
	public String updateDomainByFieldId(@RequestBody Domain domain, @PathVariable("fieldId") String fid) {

		Long fieldId = Long.parseLong(fid);
		
		Boolean b=dService.updateDomainByFieldId(fieldId, domain);
		if(b)
		return "Domain for the given field updated successfully";
		else
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
	}
	
	@RequestMapping(value = "/{domainId}/fields/{fieldId}", method = RequestMethod.PUT)
	public String updateFieldByDomainId(@PathVariable("domainId") String did, @PathVariable("fieldId") String fid) {

		Long domainId = Long.parseLong(did);
		Long fieldId = Long.parseLong(fid);
		Boolean b=dService.updateFieldByDomainId(domainId, fieldId);
		if(b)
		return "Domain for the given field updated successfully";
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}

	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.DELETE)
	public String deleteDomainByFieldId(@PathVariable ("fieldId") String fid)
	{
		Long fieldId = Long.parseLong(fid);
		Boolean b=dService.deleteDomainByFieldId(fieldId);
		if(b)
			return "Domain for the given field deleted successfully";
		else
			throw new ResourceNotFoundException("Field Id not found -  "+fieldId);
			
	}

}
