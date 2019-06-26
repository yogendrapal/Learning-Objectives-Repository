package com.LearningObjectiveRepo.taxonomy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(value = "/api/taxonomies")
public class TaxonomyController {
	
	@Autowired
	private TaxonomyService taxoService;
	
	public static class Taxo{
		private String taxoName;
		private String taxodescription;

		public Taxo() {
		}

		

		public String getTaxoName() {
			return taxoName;
		}

		public void setTaxoName(String taxoName) {
			this.taxoName = taxoName;
		}

		public String getTaxodescription() {
			return taxodescription;
		}

		public void setTaxodescription(String taxodescription) {
			this.taxodescription = taxodescription;
		}
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value="",method=RequestMethod.POST)
	public Message createTaxo(@RequestBody Taxo t)
	{
		taxoService.createTaxo(t);
		Message m=new Message();
		m.setMessage("Taxonomy submitted successfully");
		return m;
		}
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="",method=RequestMethod.GET)
	public List<Taxonomy> getTaxo()
	{
		List<Taxonomy> t = taxoService.getTaxo();
		return t;
	}
		
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/{taxoId}",method=RequestMethod.GET)
	public Taxonomy readTaxoByTaxoId(@PathVariable ("taxoId") String tId)
	{
		Long taxoId = Long.parseLong(tId);
		Taxonomy t = taxoService.readTaxoByTaxoId(taxoId);
		if (t == null)
			throw new ResourceNotFoundException("Taxonomy id not found - " + taxoId);
		return t;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{taxoId}", method = RequestMethod.PUT)
	public Message updateTaxoByTaxoId(@RequestBody Taxo t, @PathVariable("taxoId") String id) {

		Long taxoId = Long.parseLong(id);
		String taxoName = t.getTaxoName();
		Boolean b = taxoService.updateTaxoByTaxoId(taxoId, taxoName);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Taxonomy updated successfully");
			return m;
		}
		throw new ResourceNotFoundException("Taxonomy having name - " + taxoName+ " already present");
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value="/los/{loId}",method=RequestMethod.POST)
	public Message createTaxoByLoId(@RequestBody Taxo t, @PathVariable ("loId") String lid)
	{
		String taxo = t.getTaxoName();
		Long loId=Long.parseLong(lid);
		Boolean b=taxoService.createTaxoByLoId(taxo,loId);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Taxonomy corresponding to the given learning objective added successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/{taxoId}/los",method=RequestMethod.GET)
	public List<LearningObjective> readLosByTaxoId(@PathVariable ("taxoId") String tId)
	{
		Long taxoId = Long.parseLong(tId);
		List<LearningObjective> lo = taxoService.readLosByTaxoId(taxoId);
		if (lo == null || lo.isEmpty())
			throw new ResourceNotFoundException("Taxonomy id not found - " + taxoId);
		return lo;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value="/los/{loId}",method=RequestMethod.GET)
	public Taxonomy readTaxoByLoId(@PathVariable ("loId") String lId)
	{
		Long loId = Long.parseLong(lId);
		Taxonomy t = taxoService.readTaxoByLoId(loId);
		if (t == null )
			throw new ResourceNotFoundException("Learning Objective id not related to any taxonomy - " + loId);
		return t;
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.PUT)
	public Message updateTaxoByLoId(@RequestBody Taxo t, @PathVariable("loId") String lid) {

		Long loId = Long.parseLong(lid);
		String taxoName = t.getTaxoName();
		Boolean b=taxoService.updateTaxoByLoId(loId, taxoName);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Taxonomy corresponding to the given learning objective updated successfully");
			return m;	
		}
		else
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
	}
	
	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/{taxoId}/los/{loId}", method = RequestMethod.PUT)
	public Message updateLoByTaxoId(@PathVariable("taxoId") String tid, @PathVariable("loId") String lid) {

		Long loId = Long.parseLong(lid);
		Long taxoId = Long.parseLong(tid);
		Boolean b=taxoService.updateLoByTaxoId(loId, taxoId);
		if(b)
		{
			Message m=new Message();
			m.setMessage("Taxonomy corresponding to the given learning objective added successfully");
			return m;
		}
		else
			throw new ResourceNotFoundException("Updation not possible  ");
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value="/{taxoId}",method=RequestMethod.DELETE)
	public Message deleteTaxoByTaxoId(@PathVariable ("taxoId") String tid)
	{
		Long taxoId = Long.parseLong(tid);
		Boolean b=taxoService.deleteTaxoByTaxoId(taxoId);
		if(!b)
			throw new ResourceNotFoundException("Taxonomy Id not found -  "+taxoId);
		else
			{
			Message m=new Message();
			m.setMessage("Taxonomy deleted having Id - "+taxoId);
			return m;
			}
			
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value="/los/{loId}",method=RequestMethod.DELETE)
	public Message deleteTaxoByLoId(@PathVariable ("loId") String lid)
	{
		Long loId = Long.parseLong(lid);
		Boolean b=taxoService.deleteTaxoByLoId(loId);
		if(b)
			{
			Message m=new Message();
			m.setMessage("Taxonomy for the given learning objective deleted successfully");
			return m;
			}
		else
			throw new ResourceNotFoundException("Learning Objective Id not found -  "+loId);
			
	}

	
}
