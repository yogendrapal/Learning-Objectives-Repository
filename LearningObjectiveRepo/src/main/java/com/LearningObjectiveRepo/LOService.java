package com.LearningObjectiveRepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LearningObjectiveRepo.video.Video;
import com.LearningObjectiveRepo.video.VideoIdFromURL;
import com.LearningObjectiveRepo.video.VideoRepository;

@Service
public class LOService {

	@Autowired
	private LORepository loRepository;

	@Autowired
	private VideoRepository vrepo;

	public void createVideo_Lo(String lObj, String source, String sourceId) {

		Video v = vrepo.findBySourceId(sourceId);
		LearningObjective lo = loRepository.findByLObjective(lObj);

		if (v == null && lo == null) {

			Video vNew = new Video(source, sourceId);
			LearningObjective lNew = new LearningObjective(lObj);
			vNew.getLo().add(lNew);
			//lNew.getVideo().add(vNew);
			loRepository.save(lNew);
			vrepo.save(vNew);

		}

		if (v != null && lo == null) {

			LearningObjective lNew = new LearningObjective(lObj);
			v.getLo().add(lNew);
			//lNew.getVideo().add(v);
			loRepository.save(lNew);
			vrepo.save(v);
		}

		if (v == null && lo != null) {

			Video vNew = new Video(source, sourceId);
			lo.getVideo().add(vNew);
			//vNew.getLo().add(lo);
			vrepo.save(vNew);
			loRepository.save(lo);

		}

	}

	public void createLo(String lObj) {
		LearningObjective lo = loRepository.findByLObjective(lObj);
		if (lo == null) {
			LearningObjective lNew = new LearningObjective(lObj);
			loRepository.save(lNew);
		}

	}

	public void createVideo(String url) {
		String sourceId = null;
		String source = null;
		Map<String, String> map = VideoIdFromURL.getUrlId(url);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			source = entry.getKey();
			sourceId = entry.getValue();
		}
		Video v = vrepo.findBySourceId(sourceId);
		if (v == null) {
			Video vNew = new Video(source, sourceId);
			vrepo.save(vNew);
		}

	}

	public List<LearningObjective> readLoBySourceId(String sourceId) {

		Video v = vrepo.findBySourceId(sourceId);
		if (v != null) {
			return v.getLo();
		}
		return null;
	}

	public List<LearningObjective> readLoBySource(String source, String sourceId) {
		List<Video> video = vrepo.findBySource(source);
		for (Video v : video) {
			if (v.getSourceId().equals(sourceId)) {
				return v.getLo();
			}
		}
		return null;
	}

	public LearningObjective readLoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		if (lo != null) {
			return lo;
		}

		return null;
	}

	public List<Video> readVideoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		if (lo != null) {
			return lo.getVideo();
		}
		return null;
	}

	public Video readVideoByVideoId(Long videoId) {
		Video v = vrepo.findByVideoId(videoId);
		if (v != null) {
			return v;
		}
		return null;
	}

	public void updateLoByLoId(Long loId, String lobj) {
		LearningObjective lo= loRepository.findByLoId(loId);
		if(lo == null) {
		lo = new LearningObjective(lobj);	
		}
		else {
		lo.setlObjective(lobj);
		}
		loRepository.save(lo);
	}

	public void updateVideoByVideoId(Long videoId, String url) {
	Video v = vrepo.findByVideoId(videoId);
	String sourceId = null;
	String source = null;
	Map<String, String> map = VideoIdFromURL.getUrlId(url);
	for (Map.Entry<String, String> entry : map.entrySet()) {
		source = entry.getKey();
		sourceId = entry.getValue();
		}
	if(v == null) {
		v = new Video (source,sourceId);
	}
	else
	{
		v.setSource(source);
		v.setSourceId(sourceId);
	}
	vrepo.save(v);
		
	}

	public void deleteLoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		
		if(lo!=null) {
		
			//lo.getVideo().clear();
		 // loRepository.delete(lo);
		  loRepository.deleteFromLo(loId);
		
	}
		}
	public void deleteVideoByVideoId(Long vId) {
		Video v = vrepo.findByVideoId(vId);
		
		if(v!=null)
		vrepo.deleteFromVideo(vId);
	}
   

}
