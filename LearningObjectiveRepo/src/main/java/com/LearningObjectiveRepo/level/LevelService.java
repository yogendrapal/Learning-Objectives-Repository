package com.LearningObjectiveRepo.level;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.taxonomy.TaxonomyRepository;
import com.LearningObjectiveRepo.verb.Verb;
import com.LearningObjectiveRepo.verb.VerbRepository;
@Service
public class LevelService {

	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private TaxonomyRepository tRepository;
	@Autowired
	private VerbRepository vRepository;
	
	public void createLevel(Level lvl) {
		Level level = levelRepository.findByLevelName(lvl.getLevelName());
		if(level==null) {
		levelRepository.save(lvl);
		}
	}
	public Taxonomy createLevelByTaxonomy(Long taxoId,Level lvl) {
		Taxonomy taxo = tRepository.findByTaxoId(taxoId);
		Level level = levelRepository.findByLevelName(lvl.getLevelName());
		if(taxo != null && level == null ) {
		lvl.setTaxo(taxo);
		levelRepository.save(lvl);
		return taxo;
		}
		else if(taxo != null && level != null) {
			level.setTaxo(taxo);
			levelRepository.save(level);
			return taxo;
		}
	    return null;
	}
	public List<Level> getLevelByTaxoId(Long tId) {
		Taxonomy taxo = tRepository.findByTaxoId(tId);
		List<Level> lvl = taxo.getLevel();
		if(lvl==null)
		 return null;
		return lvl;
	}
	public Level getLevelByLevelId(Long lId) {
		Level lvl = levelRepository.findByLevelId(lId);
		if(lvl==null)
		 return null;
		return lvl;
	}
	public Boolean updateLevelByLevelId(Level lvl, Long lId) {
		Level l  = levelRepository.findByLevelId(lId);
		Level level = levelRepository.findByLevelName(lvl.getLevelName());
		if(level != null)
			return false;
		if(l!=null) {
		String levelName = lvl.getLevelName();
		l.setLevelName(levelName);
		levelRepository.save(l);
		}else {
			levelRepository.save(lvl);
		}
		return true;
	}
	public Boolean deleteLevelByLevelId(Long lId) {
		Level l = levelRepository.findByLevelId(lId);
		if(l!=null) {
			l.setTaxo(null);
			for(Verb v : l.getVerb() ) {
				v.setLevel(null);
				vRepository.save(v);
				}
			l.getVerb().clear();
			levelRepository.delete(l);
			return true;
		}
			
		return false;
	}
	public Boolean deleteLevelByTaxoId(Long tId) {
		Taxonomy t = tRepository.findByTaxoId(tId);
		if(t!=null)
		{
			
			for(Level l : t.getLevel()) {
				l.setTaxo(null);
				levelRepository.save(l);
				}
		
			return true;
		}
		return false;
	}
	public Boolean updateLevelBytaxoId(Long levelId, Long taxoId) {
		Level l = levelRepository.findByLevelId(levelId);
		Taxonomy t = tRepository.findByTaxoId(taxoId);
		if(l!=null && t!=null) {
		l.setTaxo(t);
		levelRepository.save(l);
		return true;
		}
		return false;
	}
	public List<Level> getLevel() {
		// TODO Auto-generated method stub
		List<Level> lvl = levelRepository.findAll();
		return lvl;
	}
}