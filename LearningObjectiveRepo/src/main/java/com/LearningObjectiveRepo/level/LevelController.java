package com.LearningObjectiveRepo.level;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.taxonomy.Taxonomy;

@RestController
@RequestMapping(value = "/api/levels")
@CrossOrigin(origins="*",allowedHeaders="*")
public class LevelController {
	@Autowired
	private LevelService levelService;
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String createLevel(@RequestBody Level lvl) {
		levelService.createLevel(lvl);
		return "Level submitted successfully";

	}
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Level> getLevel() {
	  List<Level> l = 	levelService.getLevel();
	  return l;
	

	}
	@RequestMapping(value = "/taxonomies/{taxoid}", method = RequestMethod.POST)
	public String createLevelByTaxonomy(@PathVariable("taxoid") String taxoId ,@RequestBody Level lvl) {
		Long tId = Long.parseLong(taxoId);
		Taxonomy t = levelService.createLevelByTaxonomy(tId,lvl);
		if(t!=null)
		return "Level submitted successfully";
		else throw new ResourceNotFoundException("Taxo id is not present");
	}
	@RequestMapping(value = "taxonomies/{taxoid}", method = RequestMethod.GET)
	public List<Level> getLevelByTaxoId(@PathVariable("taxoid")String taxoId) {
		Long tId = Long.parseLong(taxoId);
		List<Level> lvl = levelService.getLevelByTaxoId(tId);
		if(lvl == null) {
			throw new ResourceNotFoundException("Taxo id not found - " + taxoId);
		}
		else if(lvl.isEmpty())
			throw new ResourceNotFoundException("List is Empty");
		return lvl;
	}
	@RequestMapping(value = "/{levelid}", method = RequestMethod.GET)
	public Level getLevelByLevelId(@PathVariable("levelid")String levelId) {
		Long lId = Long.parseLong(levelId);
		Level lvl = levelService.getLevelByLevelId(lId);
		if(lvl == null) {
			throw new ResourceNotFoundException("Level id not found ");
		}
		return lvl;
	}
	@RequestMapping(value = "/{levelid}", method = RequestMethod.PUT)
	public String updateLevelByLevelId(@RequestBody Level lvl,@PathVariable("levelid")String levelId) {
		Long lId = Long.parseLong(levelId);
         Boolean b = levelService.updateLevelByLevelId(lvl,lId);
         if(b)
		return "Udpdated successfully";
         throw new ResourceNotFoundException("Level having name -  "+lvl.getLevelName()+" already present." );
	}
	@RequestMapping(value = "/{levelid}/taxonomies/{taxoid}", method = RequestMethod.PUT)
	public String updateLevelByTaxoId(@PathVariable("levelid")String levelId,@PathVariable("taxoid")String taxoId) {
		Long lId = Long.parseLong(levelId);
		Long tId = Long.parseLong(taxoId);
         Boolean b = levelService.updateLevelBytaxoId(lId,tId);
         if(b)
		return "Udpdated successfully";
 		throw new ResourceNotFoundException("Udpdation not possible");
	}
	@RequestMapping(value = "/{levelid}", method = RequestMethod.DELETE)
	public String deleteLevelByLevelId(@PathVariable("levelid")String levelId) {
		Long lId = Long.parseLong(levelId);
         Boolean b = levelService.deleteLevelByLevelId(lId);
        if(b)
		return "Deleted successfully";
        throw new ResourceNotFoundException("Level Id is not valid");
	}
	@RequestMapping(value = "taxonomies/{taxoid}", method = RequestMethod.DELETE)
	public String deleteLevelTaxoId(@PathVariable("taxoid")String taxoId) {
		Long tId = Long.parseLong(taxoId);
         Boolean b = levelService.deleteLevelByTaxoId(tId);
        if(b)
		return "Deleted successfully";
        throw new  ResourceNotFoundException("Taxonomy Id is not valid");
	}
	
    
}
