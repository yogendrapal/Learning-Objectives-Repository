package com.LearningObjectiveRepo.taxonomy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
@RestController
@RequestMapping(value = "/api/taxonomies")
public class TaxonomyController {
	
	@Autowired
	private TaxonomyService taxoService;
	
	public static class Taxo{
		private String taxo;

		public Taxo() {
		}

		public String getTaxo() {
			return taxo;
		}

		public void setTaxo(String taxo) {
			this.taxo = taxo;
		}
		
	}
	@RequestMapping(value="",method=RequestMethod.POST)
	public String createTaxo(@RequestBody Taxo t)
	{
		String taxo = t.getTaxo();
		taxoService.createTaxo(taxo);
		return "Taxonomy submitted successfully";
	}
	
	@RequestMapping(value="/{taxoId}",method=RequestMethod.GET)
	public Taxonomy readTaxoByTaxoId(@PathVariable ("taxoId") String tId)
	{
		Long taxoId = Long.parseLong(tId);
		Taxonomy t = taxoService.readTaxoByTaxoId(taxoId);
		if (t == null)
			throw new ResourceNotFoundException("Taxonomy id not found - " + taxoId);
		return t;
	}
	
	@RequestMapping(value = "/{taxoId}", method = RequestMethod.PUT)
	public String updateTaxoByTaxoId(@RequestBody Taxo t, @PathVariable("taxoId") String id) {

		Long taxoId = Long.parseLong(id);
		String taxoName = t.getTaxo();
		Boolean b = taxoService.updateTaxoByTaxoId(taxoId, taxoName);
		if(b)
		return "taxonomy updated successfully";
		throw new ResourceNotFoundException("Taxonomy having name - " + taxoName+ " already present");
	}
	
	@RequestMapping(value="/los/{loId}",method=RequestMethod.POST)
	public String createTaxoByLoId(@RequestBody Taxo t, @PathVariable ("loId") String lid)
	{
		String taxo = t.getTaxo();
		Long loId=Long.parseLong(lid);
		Boolean b=taxoService.createTaxoByLoId(taxo,loId);
		if(b)
		return "Taxonomy corresponding to the given learning objective added successfully";
		else
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
	}
	
	@RequestMapping(value="/{taxoId}/los",method=RequestMethod.GET)
	public List<LearningObjective> readLosByTaxoId(@PathVariable ("taxoId") String tId)
	{
		Long taxoId = Long.parseLong(tId);
		List<LearningObjective> lo = taxoService.readLosByTaxoId(taxoId);
		if (lo == null || lo.isEmpty())
			throw new ResourceNotFoundException("Taxonomy id not found - " + taxoId);
		return lo;
	}
	
	@RequestMapping(value="/los/{loId}",method=RequestMethod.GET)
	public Taxonomy readTaxoByLoId(@PathVariable ("loId") String lId)
	{
		Long loId = Long.parseLong(lId);
		Taxonomy t = taxoService.readTaxoByLoId(loId);
		if (t == null )
			throw new ResourceNotFoundException("Learning Objective id not related to any taxonomy - " + loId);
		return t;
	}
	
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.PUT)
	public String updateTaxoByLoId(@RequestBody Taxo t, @PathVariable("loId") String lid) {

		Long loId = Long.parseLong(lid);
		String taxoName = t.getTaxo();
		Boolean b=taxoService.updateTaxoByLoId(loId, taxoName);
		if(b)
		return "Taxonomy for the given learning objective updated successfully";
		else
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
	}
	
	@RequestMapping(value = "/{taxoId}/los/{loId}", method = RequestMethod.PUT)
	public String updateLoByTaxoId(@PathVariable("taxoId") String tid, @PathVariable("loId") String lid) {

		Long loId = Long.parseLong(lid);
		Long taxoId = Long.parseLong(tid);
		Boolean b=taxoService.updateLoByTaxoId(loId, taxoId);
		if(b)
		return "Taxonomy for the given learning objective updated successfully";
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	
	@RequestMapping(value="/{taxoId}",method=RequestMethod.DELETE)
	public String deleteTaxoByTaxoId(@PathVariable ("taxoId") String tid)
	{
		Long taxoId = Long.parseLong(tid);
		Boolean b=taxoService.deleteTaxoByTaxoId(taxoId);
		if(!b)
			throw new ResourceNotFoundException("Taxonomy Id not found -  "+taxoId);
		else
			return "Taxonomy deleted having Id - "+taxoId;
			
	}
	
	@RequestMapping(value="/los/{loId}",method=RequestMethod.DELETE)
	public String deleteTaxoByLoId(@PathVariable ("loId") String lid)
	{
		Long loId = Long.parseLong(lid);
		Boolean b=taxoService.deleteTaxoByLoId(loId);
		if(b)
			return "Taxonomy for the given learning objective deleted successfully";
		else
			throw new ResourceNotFoundException("Learning Objective Id not found -  "+loId);
			
	}

	
}
