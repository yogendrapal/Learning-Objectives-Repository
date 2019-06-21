package com.LearningObjectiveRepo.type;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.LORepository;
import com.LearningObjectiveRepo.LearningObjective;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.level.Level;
import com.LearningObjectiveRepo.level.LevelRepository;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.taxonomy.TaxonomyRepository;

import com.LearningObjectiveRepo.type.TypeController.LoType;
import com.LearningObjectiveRepo.verb.Verb;
import com.LearningObjectiveRepo.verb.VerbRepository;

@Service
public class TypeService {

	@Autowired
	private TypeRepository tRepository;
	@Autowired
	private LORepository loRepository;
	@Autowired
	private TaxonomyRepository taxoRepository;
	@Autowired
	private LevelRepository lRepository;
	@Autowired
	private VerbRepository vRepository;
	
	public Message createType(LoType loType) {
		
		Long taxoId=Long.parseLong(loType.getTaxoId());
		Long lId=Long.parseLong(loType.getLevelId());
		Long vId=Long.parseLong(loType.getVerbId());
		
		TypeId tId=new TypeId(taxoId,lId,vId);
		Optional<Type> t=tRepository.findById(tId);
		LearningObjective lo = loRepository.findByLObjective(loType.getLo());
		
		if(!t.isPresent() && lo==null)
			{ 
			  Type t1=new Type(tId);
			  lo=new LearningObjective();
			  lo.setlObjective(loType.getLo());
			  Taxonomy taxo=taxoRepository.findByTaxoId(taxoId);
			  t1.setTaxonomy(taxo);
			  Level l=lRepository.findByLevelId(lId);
			  t1.setLevel(l);
			  Verb v=vRepository.findByVerbId(vId);
			  t1.setVerb(v);
			  
			  
			  t1.gettId().setTaxoId(taxoId);
			  t1.gettId().setLevelId(lId);
			  t1.gettId().setVerbId(vId);
			  
			  lo.getType().add(t1);
			
			  loRepository.save(lo);
			  tRepository.save(t1);
			  
			}
		
		else if(lo==null && t.isPresent())
		{
			lo=new LearningObjective();
			lo.setlObjective(loType.getLo());
			  lo.getType().add(t.get());
			  loRepository.save(lo);
		}
		
		else if(!t.isPresent() && lo!=null)
		{
			Type t1=new Type(tId);
			  lo=new LearningObjective();
			  lo.setlObjective(loType.getLo());
			  Taxonomy taxo=taxoRepository.findByTaxoId(taxoId);
			  t1.setTaxonomy(taxo);
			  Level l=lRepository.findByLevelId(lId);
			  t1.setLevel(l);
			  Verb v=vRepository.findByVerbId(vId);
			  t1.setVerb(v);
			  
			  
			  t1.gettId().setTaxoId(taxoId);
			  t1.gettId().setLevelId(lId);
			  t1.gettId().setVerbId(vId);
			  
		      lo.getType().add(t1);
			  loRepository.save(lo);
			  tRepository.save(t1);	

		}
		else
		{
			lo.getType().add(t.get());
			  loRepository.save(lo);	
		}
	   Message m=new Message();
	   m.setMessage("Learning objective added successfully.");
	   return m;
	}

}
