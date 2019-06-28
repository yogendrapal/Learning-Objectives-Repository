package com.LearningObjectiveRepo.verb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.level.Level;
import com.LearningObjectiveRepo.level.LevelRepository;

@Service
public class VerbService {

	@Autowired
	private VerbRepository vRepository;

	@Autowired
	private LevelRepository lRepository;

	public void createVerb(String vName) {
		Verb v = vRepository.findByVerbName(vName);
		if (v == null) {
			v = new Verb(vName);
			vRepository.save(v);
		}

	}

	public Verb readVerbByVerbId(Long verbId) {
		Verb v = vRepository.findByVerbId(verbId);
		if (v != null) {
			return v;
		}
		return null;
	}

	public void updateVerbByVerbId(Long verbId, String verbName) {
		Verb v = vRepository.findByVerbId(verbId);
		if (v == null) {
			v = new Verb(verbName);
		} else {
			v.setVerbName(verbName);
		}
		vRepository.save(v);

	}

	public Boolean createVerbByLevelId(String verbName, Long lId) {
		Level l = lRepository.findByLevelId(lId);
		Verb v = vRepository.findByVerbName(verbName);
		if (l != null && v != null) {
			v.setLevel(l);
			vRepository.save(v);
			return true;
		} else if (l != null && v == null) {
			v = new Verb(verbName);
			v.setLevel(l);
			vRepository.save(v);
			return true;
		} else
			return false;

	}

	public List<Verb> readVerbByLevelId(Long lId) {
		Level l = lRepository.findByLevelId(lId);
		if (l != null)
			return l.getVerb();
		return null;
	}

	public Level readLevelByVerbId(Long verbId) {
		Verb v = vRepository.findByVerbId(verbId);
		if (v != null) {
			return v.getLevel();
		}
		return null;
	}

	public Boolean updateLevelByVerbId(Long verbId, Long lId) {
		Level l = lRepository.findByLevelId(lId);
		Verb v = vRepository.findByVerbId(verbId);
		if (l != null && v != null) {
			v.setLevel(l);
			vRepository.save(v);
			return true;
		}
		return false;
	}

	public Verb deleteVerbByVerbId(Long verbId) {
		Verb v = vRepository.findByVerbId(verbId);
		if (v != null) {
			vRepository.delete(v);
			return v;
		}
		return null;
	}

	public List<Verb> getVerb() {
		return vRepository.findAll();
	}

	public Level readLevelByVerb(Long vId) {
		Verb verb = vRepository.findByVerbId(vId);
		if (verb != null) {
			return verb.getLevel();
		}
		return null;
	}

}
