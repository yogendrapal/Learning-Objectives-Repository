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

	
	 /**
	   * Create videos and their corresponding learning objectives.
	   *
	   * @param learning objective, video source and video source Id
	   */
	
	public void createVideo_Lo(String lObj, String source, String sourceId) {

		Video v = vrepo.findBySourceId(sourceId);
		LearningObjective lo = loRepository.findByLObjective(lObj);

		//Both video and lo are absent in database
		if (v == null && lo == null) {

			Video vNew = new Video(source, sourceId);
			LearningObjective lNew = new LearningObjective(lObj);
			vNew.getLo().add(lNew);
			loRepository.save(lNew);
			vrepo.save(vNew);

		}

		//Video present but lo is not
		else if (v != null && lo == null) {

			LearningObjective lNew = new LearningObjective(lObj);
			v.getLo().add(lNew);
			loRepository.save(lNew);
			vrepo.save(v);
		}

		//Lo is present in database but not the video
		else if (v == null && lo != null) {

			Video vNew = new Video(source, sourceId);
			lo.getVideo().add(vNew);
			vrepo.save(vNew);
			loRepository.save(lo);

		}
		

	}


	 /**
	   * Create learning objectives.
	   *
	   * @param learning objective
	   */
	
	public void createLo(String lObj) {
		LearningObjective lo = loRepository.findByLObjective(lObj);
		if (lo == null) {
			LearningObjective lNew = new LearningObjective(lObj);
			loRepository.save(lNew);
		}

	}


	 /**
	   * Create video.
	   *
	   * @param video url
	   */
	
	public void createVideo(String url) {
		String sourceId = null;
		String source = null;
		
		//Splitting video url into source and source id
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


	 /**
	   * Read learning objectives corresponding to a particular video source Id.
	   *
	   * @param video source Id
	   */
	
	public List<LearningObjective> readLoBySourceId(String sourceId) {

		Video v = vrepo.findBySourceId(sourceId);
		if (v != null) {
			return v.getLo();
		}
		return null;
	}

	/**
	   * Read learning objectives corresponding to a particular video source and sourceId.
	   *
	   * @param video source and its sourceId
	   */
	
	public List<LearningObjective> readLoBySource(String source, String sourceId) {
		List<Video> video = vrepo.findBySource(source);
		for (Video v : video) {
			if (v.getSourceId().equals(sourceId)) {
				return v.getLo();
			}
		}
		return null;
	}

	/**
	   * Read learning objective corresponding to a particular loId.
	   *
	   * @param learning objective Id
	   */
	
	public LearningObjective readLoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		if (lo != null) {
			return lo;
		}

		return null;
	}

	/**
	   * Read list of videos corresponding to a particular loId.
	   *
	   * @param learning objective Id
	   */
	
	public List<Video> readVideoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		if (lo != null) {
			return lo.getVideo();
		}
		return null;
	}

	
	/**
	   * Read video corresponding to a particular videoId.
	   *
	   * @param videoId
	   */
	
	public Video readVideoByVideoId(Long videoId) {
		Video v = vrepo.findByVideoId(videoId);
		if (v != null) {
			return v;
		}
		return null;
	}

	/**
	   * Update learning objective corresponding to a particular loId.
	   *
	   * @param learning objective, loId
	   */
	
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

	/**
	   * Update video corresponding to a particular videoId.
	   *
	   * @param videoId and video url
	   */
	
	public void updateVideoByVideoId(Long videoId, String url) {
	Video v = vrepo.findByVideoId(videoId);
	String sourceId = null;
	String source = null;
	
	//Splitting video url into source and source Id
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
	
	/**
	   * Delete learning objective corresponding to a particular loId.
	   *
	   * @param learning objective Id
	   */
	
	public void deleteLoByLoId(Long loId) {
		LearningObjective lo = loRepository.findByLoId(loId);
		
		if(lo!=null) {
		
		  loRepository.deleteFromLo(loId);
		}
}
	
	/**
	   * Delete video corresponding to a particular videoId.
	   *
	   * @param videoId
	   */
	
	public void deleteVideoByVideoId(Long vId) {
		Video v = vrepo.findByVideoId(vId);
		
		if(v!=null)
		vrepo.deleteFromVideo(vId);
	}
   

}
