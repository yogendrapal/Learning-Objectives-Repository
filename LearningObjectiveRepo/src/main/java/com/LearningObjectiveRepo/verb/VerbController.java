package com.LearningObjectiveRepo.verb;

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
import com.LearningObjectiveRepo.level.Level;


@RestController
@RequestMapping(value="/api/secured/verbs")
public class VerbController {

	@Autowired
	private VerbService verbService;
	
	public static class verb{
		private String verbName;			

		public String getVerbName() {
			return verbName;
		}

		public void setVerbName(String verbName) {
			this.verbName = verbName;
		}
		
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Message createVerb(@RequestBody verb v)
	{
		String vName = v.getVerbName();
		verbService.createVerb(vName);
		 Message m=new Message();
  		m.setMessage("Verb submitted successfully");
  		return m;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/{verbId}",method=RequestMethod.GET)
	public Verb readVerbByVerbId(@PathVariable ("verbId") String vId)
	{
		Long verbId = Long.parseLong(vId);
		 Verb v = verbService.readVerbByVerbId(verbId);
		if (v == null)
			throw new ResourceNotFoundException("Verb id not found - " + verbId);
		return v;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{verbId}", method = RequestMethod.PUT)
	public Message updateVerbByVerbId(@RequestBody verb v, @PathVariable("verbId") String vid) {

		Long verbId = Long.parseLong(vid);
		String verbName = v.getVerbName();
		verbService.updateVerbByVerbId(verbId, verbName);
		Message m=new Message();
  		m.setMessage("Verb updated successfully");
  		return m;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value="/levels/{lId}",method=RequestMethod.POST)
	public Message createVerbByLevelId(@RequestBody verb v, @PathVariable ("lId") String id)
	{
		String verbName = v.getVerbName();
		Long lId=Long.parseLong(id);
		Boolean b=verbService.createVerbByLevelId(verbName,lId);
		if(b)
		{
			Message m=new Message();
	  		m.setMessage("Verb submitted successfully");
	  		return m;
		}
		else
			throw new ResourceNotFoundException("Level id not found - " + lId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/levels/{lId}",method=RequestMethod.GET)
	public List<Verb> readVerbByLevelId(@PathVariable ("lId") String Id)
	{
		Long lId = Long.parseLong(Id);
		List<Verb> l = verbService.readVerbByLevelId(lId);
		if (l == null || l.isEmpty())
			throw new ResourceNotFoundException("Level id not found - " + lId);
		return l;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/{verbId}/levels",method=RequestMethod.GET)
	public Level readLevelByVerbId(@PathVariable ("verbId") String vId)
	{
		Long verbId = Long.parseLong(vId);
		Level l= verbService.readLevelByVerbId(verbId);
		if (l == null )
			throw new ResourceNotFoundException("Verb id not related to any level - " + vId);
		return l;
	}

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{verbId}/levels/{lId}", method = RequestMethod.PUT)
	public Message updateLevelByVerbId(@PathVariable("verbId") String vid, @PathVariable("lId") String id) {

		Long verbId = Long.parseLong(vid);
		Long lId = Long.parseLong(id);
		Boolean b=verbService.updateLevelByVerbId(verbId, lId);
		if(b)
		{
			Message m=new Message();
	  		m.setMessage("Verb updated successfully");
	  		return m;
		}
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value="/{verbId}",method=RequestMethod.DELETE)
	public Message deleteVerbByVerbId(@PathVariable ("verbId") String vid)
	{
		Long verbId = Long.parseLong(vid);
		Verb v=verbService.deleteVerbByVerbId(verbId);
		if(v==null)
			throw new ResourceNotFoundException("Verb Id not found -  "+verbId);
		else
			{
			Message m=new Message();
	  		m.setMessage("Verb deleted successfully");
	  		return m;
			}
			
	}
	
	
}
