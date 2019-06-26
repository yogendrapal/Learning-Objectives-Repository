package com.LearningObjectiveRepo.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.field.Field;


@RestController
@RequestMapping(value="/api/domains")
public class DomainController {
	
	@Autowired
	private DomainService dService;
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Message createDomain(@RequestBody Domain domain) {
		Boolean b=dService.createDomain(domain);
		Message m=new Message();
		if(b)
		m.setMessage("Domain submitted successfully");
		else
			m.setMessage("Domain Name already exists.");
		return m;	
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/{domainId}", method = RequestMethod.GET)
	public Domain readDomainByDomainId(@PathVariable ("domainId") String dId) {
		Long domainId=Long.parseLong(dId);
		Domain d=dService.readDomainByDomainId(domainId);
		if(d==null)
			throw new ResourceNotFoundException("Domain id not found - " + domainId);
		return d;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{domainId}", method = RequestMethod.PUT)
	public Message updateDomainByDomainId(@RequestBody Domain domain,@PathVariable ("domainId") String dId) {
		Long domainId=Long.parseLong(dId);
		Boolean b=dService.updateDomainByDomainId(domain,domainId);
		if(b)
			{
			Message m=new Message();
			m.setMessage("Domain updated successfully");
			return m;
			}
        throw new ResourceNotFoundException("Domain having name -  "+domain.getDomainName()+" already present." );
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/{domainId}", method = RequestMethod.DELETE)
	public Message deleteDomainByDomainId(@PathVariable("domainId")String dId) {
		Long domainId = Long.parseLong(dId);
         Boolean b = dService.deleteDomainByDomainId(domainId);
        if(b)
		{
        	Message m=new Message();
			m.setMessage(" Domain deleted successfully");
			return m;
		}
        throw new ResourceNotFoundException("Domain Id is not valid");
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.POST)
	public Message createDomainByFieldId(@RequestBody Domain domain, @PathVariable ("fieldId") String fid)
	{
		Long fieldId=Long.parseLong(fid);
		Boolean b=dService.createDomainByFieldId(domain,fieldId);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Domain corresponding to the given field added successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/{domainId}/fields",method=RequestMethod.GET)
	public List<Field> readFieldsByDomainId(@PathVariable ("domainId") String dId)
	{
		Long domainId = Long.parseLong(dId);
		List<Field> f = dService.readFieldsByDomainId(domainId);
		if (f == null || f.isEmpty())
			throw new ResourceNotFoundException("No fields with domain id - " + domainId);
		return f;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.GET)
	public Domain readDomainByFieldId(@PathVariable ("fieldId") String fId)
	{
		Long fieldId = Long.parseLong(fId);
		Domain d = dService.readDomainByFieldId(fieldId);
		if (d == null )
			throw new ResourceNotFoundException("Field id not related to any domain - " + fieldId);
		return d;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/fields/{fieldId}", method = RequestMethod.PUT)
	public Message updateDomainByFieldId(@RequestBody Domain domain, @PathVariable("fieldId") String fid) {

		Long fieldId = Long.parseLong(fid);
		
		Boolean b=dService.updateDomainByFieldId(fieldId, domain);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Domain corresponding to the given field update successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Field id not found - " + fieldId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{domainId}/fields/{fieldId}", method = RequestMethod.PUT)
	public Message updateFieldByDomainId(@PathVariable("domainId") String did, @PathVariable("fieldId") String fid) {

		Long domainId = Long.parseLong(did);
		Long fieldId = Long.parseLong(fid);
		Boolean b=dService.updateFieldByDomainId(domainId, fieldId);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Domain corresponding to the given field updated successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}

	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value="/fields/{fieldId}",method=RequestMethod.DELETE)
	public Message deleteDomainByFieldId(@PathVariable ("fieldId") String fid)
	{
		Long fieldId = Long.parseLong(fid);
		Boolean b=dService.deleteDomainByFieldId(fieldId);
		if(b)
			{
			Message m=new Message();
			m.setMessage("Domain corresponding to the given field deleted successfully");
			return m;
			}
		else
			throw new ResourceNotFoundException("Field Id not found -  "+fieldId);
			
	}

}
