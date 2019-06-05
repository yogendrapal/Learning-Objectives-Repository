package com.LearningObjectiveRepo.taxonomy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.LORepository;
import com.LearningObjectiveRepo.LearningObjective;

@Service
public class TaxonomyService {

	@Autowired
	private TaxonomyRepository tRepository;
	
	@Autowired
	private LORepository loRepository;
	
	public void createTaxo(String taxo) {
	Taxonomy t=tRepository.findByTaxoName(taxo);
	if(t==null)
	{
		t=new Taxonomy(taxo);
		tRepository.save(t);
	}
	}
	public Taxonomy readTaxoByTaxoId(Long taxoId) {
	Taxonomy t = tRepository.findByTaxoId(taxoId);
	if (t != null) {
		return t;
	}

	return null;
	}
	public void updateTaxoByTaxoId(Long taxoId, String taxoName) {
		Taxonomy t = tRepository.findByTaxoId(taxoId);
		if (t == null) {
			t = new Taxonomy(taxoName);
		} else {
			t.setTaxoName(taxoName);
		}
		tRepository.save(t);
		
	}
	public Boolean createTaxoByLoId(String taxo, Long loId) {
		LearningObjective lo=loRepository.findByLoId(loId);
		Taxonomy t=tRepository.findByTaxoName(taxo);
		if(lo!=null && t!=null)
		{
			lo.setTaxonomy(t);
			loRepository.save(lo);
			return true;
		}
		else if(lo!=null && t==null)
		{
		t=new Taxonomy(taxo);
		tRepository.save(t);
		lo.setTaxonomy(t);
		loRepository.save(lo);
		return true;
		}
		else 
			return false;
		
		
	}
	public List<LearningObjective> readLosByTaxoId(Long taxoId) {
		Taxonomy t=tRepository.findByTaxoId(taxoId);
		if(t!=null)
		{
			return t.getLo();
		}
		return null;
	}
	
	public Taxonomy readTaxoByLoId(Long loId) {
		LearningObjective lo=loRepository.findByLoId(loId);
		if(lo!=null)
			return lo.getTaxonomy();
		return null;
	}
	
	public Boolean updateTaxoByLoId(Long loId, String taxoName) {
		LearningObjective lo=loRepository.findByLoId(loId);
		Taxonomy t=tRepository.findByTaxoName(taxoName);
		if(lo!=null && t!=null)
		{
			lo.setTaxonomy(t);
			loRepository.save(lo);
			return true;
		}
		else if(lo!=null && t==null)
		{
			t=new Taxonomy(taxoName);
			tRepository.save(t);
			lo.setTaxonomy(t);
			loRepository.save(lo);
			return true;
		}
			else 
				return false;
		
	}
	public Boolean updateLoByTaxoId(Long loId, Long taxoId) {
		
		LearningObjective lo=loRepository.findByLoId(loId);
		Taxonomy t=tRepository.findByTaxoId(taxoId);
		if(lo!=null && t!=null)
		{
			lo.setTaxonomy(t);
			loRepository.save(lo);
			return true;
		}
		else
			return false;
	}
	
	public Taxonomy deleteTaxoByTaxoId(Long taxoId) {
		Taxonomy t=tRepository.findByTaxoId(taxoId);
		if(t!=null) {
		t.getLo().clear();
		tRepository.delete(t);
		return t;
		}
		return null;
	}
	public Boolean deleteTaxoByLoId(Long loId) {
		LearningObjective lo=loRepository.findByLoId(loId);
		if(lo!=null)
		{
			lo.setTaxonomy(null);
			loRepository.save(lo);
			return true;
		}
		return false;
	}

}
