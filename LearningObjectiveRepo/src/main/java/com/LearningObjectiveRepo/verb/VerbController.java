package com.LearningObjectiveRepo.verb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.level.Level;


@RestController
@RequestMapping(value="/api/verbs")
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
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String createVerb(@RequestBody verb v)
	{
		String vName = v.getVerbName();
		verbService.createVerb(vName);
		return "Verb submitted successfully";
	}
	
	@RequestMapping(value="/{verbId}",method=RequestMethod.GET)
	public Verb readVerbByVerbId(@PathVariable ("verbId") String vId)
	{
		Long verbId = Long.parseLong(vId);
		 Verb v = verbService.readVerbByVerbId(verbId);
		if (v == null)
			throw new ResourceNotFoundException("Verb id not found - " + verbId);
		return v;
	}
	
	@RequestMapping(value = "/{verbId}", method = RequestMethod.PUT)
	public String updateVerbByVerbId(@RequestBody verb v, @PathVariable("verbId") String vid) {

		Long verbId = Long.parseLong(vid);
		String verbName = v.getVerbName();
		verbService.updateVerbByVerbId(verbId, verbName);
		return "Verb updated successfully";
	}
	
	@RequestMapping(value="/levels/{lId}",method=RequestMethod.POST)
	public String createVerbByLevelId(@RequestBody verb v, @PathVariable ("lId") String id)
	{
		String verbName = v.getVerbName();
		Long lId=Long.parseLong(id);
		Boolean b=verbService.createVerbByLevelId(verbName,lId);
		if(b)
		return "Verb corresponding to the given level added successfully";
		else
			throw new ResourceNotFoundException("Level id not found - " + lId);
	}
	
	@RequestMapping(value="/levels/{lId}",method=RequestMethod.GET)
	public List<Verb> readVerbByLevelId(@PathVariable ("lId") String Id)
	{
		Long lId = Long.parseLong(Id);
		List<Verb> l = verbService.readVerbByLevelId(lId);
		if (l == null || l.isEmpty())
			throw new ResourceNotFoundException("Level id not found - " + lId);
		return l;
	}
	
	@RequestMapping(value="/{verbId}/levels",method=RequestMethod.GET)
	public Level readLevelByVerbId(@PathVariable ("verbId") String vId)
	{
		Long verbId = Long.parseLong(vId);
		Level l= verbService.readLevelByVerbId(verbId);
		if (l == null )
			throw new ResourceNotFoundException("Verb id not related to any level - " + vId);
		return l;
	}

	@RequestMapping(value = "/{verbId}/levels/{lId}", method = RequestMethod.PUT)
	public String updateLevelByVerbId(@PathVariable("verbId") String vid, @PathVariable("lId") String id) {

		Long verbId = Long.parseLong(vid);
		Long lId = Long.parseLong(id);
		Boolean b=verbService.updateLevelByVerbId(verbId, lId);
		if(b)
		return "Level for the given verb updated successfully";
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	@RequestMapping(value="/{verbId}",method=RequestMethod.DELETE)
	public String deleteVerbByVerbId(@PathVariable ("verbId") String vid)
	{
		Long verbId = Long.parseLong(vid);
		Verb v=verbService.deleteVerbByVerbId(verbId);
		if(v==null)
			throw new ResourceNotFoundException("Verb Id not found -  "+verbId);
		else
			return "Verb deleted having Id - "+verbId;
			
	}
	
	
}
