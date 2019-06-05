package com.LearningObjectiveRepo.level;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.taxonomy.TaxonomyRepository;
@Service
public class LevelService {

	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private TaxonomyRepository tRepository;
	public void createLevel(Level lvl) {
		Level level = levelRepository.findByLevelName(lvl.getLevelName());
		if(level==null)
		levelRepository.save(lvl);
	}
	public Taxonomy createLevelByTaxonomy(Long taxoId,Level lvl) {
		Taxonomy taxo = tRepository.findByTaxoId(taxoId);
		if(taxo != null) {
		lvl.setTaxo(taxo);
		levelRepository.save(lvl);
		return taxo;
		}
	    return null;
	}
	public List<Level> getLevelByTaxoId(Long tId) {
		Taxonomy taxo = tRepository.findLevelByTaxoId(tId);
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
	public void updateLevelByLevelId(Level lvl, Long lId) {
		Level l  = levelRepository.findByLevelId(lId);
		if(l!=null) {
		String levelName = lvl.getLevelName();
		l.setLevelName(levelName);
		levelRepository.save(l);
		}else {
			levelRepository.save(lvl);
		}
	}
}