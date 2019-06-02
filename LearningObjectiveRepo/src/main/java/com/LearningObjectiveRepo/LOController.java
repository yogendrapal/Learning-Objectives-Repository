package com.LearningObjectiveRepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.video.Video;
import com.LearningObjectiveRepo.video.VideoIdFromURL;

@RestController
@RequestMapping(value = "/api")
public class LOController {

	@Autowired
	private LOService loService;

	
	 /**
	   * Create videos and their corresponding learning objectives.
	   *
	   * @param video url and learning objective in json format
	   */
	
	@RequestMapping(value = "/videos/los", method = RequestMethod.POST)
	public void createVideo_Lo(@RequestBody ReqFormat rf) {
		String url = rf.geturl();
		String lObj = rf.getlo();
		String sourceId = null;
		String source = null;

		Map<String, String> map = VideoIdFromURL.getUrlId(url);
		for (Map.Entry<String, String> entry : map.entrySet()) {
			source = entry.getKey();
			sourceId = entry.getValue();
		}

		loService.createVideo_Lo(lObj, source, sourceId);

	}

	public static class Lo {
		private String lo;

		public String getLo() {
			return lo;
		}

		public void setLo(String lo) {
			this.lo = lo;
		}

		public Lo() {

		}

	}

	 /**
	   * Create learning objective.
	   *
	   * @param learning objective in json format
	   */
	
	@RequestMapping(value = "/los", method = RequestMethod.POST)
	public void createLO(@RequestBody Lo lobj) {
		String lo = lobj.getLo();
		loService.createLo(lo);

	}

	public static class video {
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public video() {

		}
	}

	/**
	   * Create videos.
	   *
	   * @param video url in json format
	   */
	
	@RequestMapping(value = "/videos", method = RequestMethod.POST)
	public void createVideo(@RequestBody video v) {
		String url = v.getUrl();
		loService.createVideo(url);

	}

	
	/**
	   * Gets LearningObjectives by Video.sourceId.
	   *
	   * @return the list of Learning Objectives by video.sourceId
	   */
	
	@RequestMapping(value = "/los/video/{sourceId}", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> readLoBySourceId(@PathVariable String sourceId) {

		List<LearningObjective> lo=loService.readLoBySourceId(sourceId);
		if(lo==null)
			throw new ResourceNotFoundException("Source id not found - " + sourceId);
		return lo;
	}

	/**
	   * Gets LearningObjectives by Video.source and video.sourceId.
	   *
	   * @return the list of Learning Objectives
	   */
	
	@RequestMapping(value = "/los/video/{source}/{sourceId}", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> readLoBySource(@PathVariable String source,
			@PathVariable String sourceId) {

		List<LearningObjective> lo= loService.readLoBySource(source, sourceId);
		if(lo==null)
			throw new ResourceNotFoundException("Source id not found - " + sourceId);
		return lo;

	}

	
	/**
	   * Gets LearningObjectives by LoId.
	   *
	   * @return the Learning Objective for the particular LoId
	   */
	
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.GET)
	public @ResponseBody LearningObjective readLoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		LearningObjective lo= loService.readLoByLoId(loId);
		if(lo==null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		return lo;
	}

	/**
	   * Gets videos by particular learning objective Id.
	   *
	   * @return the list of videos
	   */
	
	@RequestMapping(value = "/videos/lo/{loId}", method = RequestMethod.GET)
	public @ResponseBody List<Video> readVideoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		List<Video> v=loService.readVideoByLoId(loId);
		if(v==null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		return v;
	}

	/**
	   * Gets video for a particular videoId
	   *
	   * @return video
	   */
	
	@RequestMapping(value = "/videos/{videoId}", method = RequestMethod.GET)
	public @ResponseBody Video readVideoByVideoId(@PathVariable("videoId") String vId) {

		Long videoId = Long.parseLong(vId);
		Video v= loService.readVideoByVideoId(videoId);
		if(v==null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		return v;
	}
	
	/**
	   * Update learning objective by LoId.
	   *
	   * @param learning objective Id passed as a part of url
	   * @param learning objective in json format passed in request body
	   */
	
	@RequestMapping(value="/los/{loId}",method = RequestMethod.PUT)
	public void updateLoByLoId(@RequestBody Lo lo ,@PathVariable("loId") String id) {
      
		Long loId = Long.parseLong(id);
		String lobj=lo.getLo();
		 loService.updateLoByLoId(loId,lobj);
	}
	
	/**
	   * Update video by videoId
	   *
	   * @param videoId passed as a part of url
	   * @param video url in json format passed in request body
	   */
	
	@RequestMapping(value="/videos/{videoId}",method = RequestMethod.PUT)
	public void updateVideoByVideoId(@RequestBody video v ,@PathVariable("videoId") String id) {
      
		Long videoId = Long.parseLong(id);
		String url=v.getUrl();
		 loService.updateVideoByVideoId(videoId,url);
	}
	
	 /**
	   * Delete learning objective by LoId.
	   *
	   * @param loId passed as a part of url
	   */
	
	@RequestMapping(value="/los/{loId}",method = RequestMethod.DELETE)
	public void deleteLoByLoId(@PathVariable("loId") String id) {
      
		Long loId = Long.parseLong(id);
		System.out.println(loId);
		 loService.deleteLoByLoId(loId);
	}
	
	/**
	   * Delete video by videoId.
	   *
	   * @param videoId passed as a part of url
	   */
	
	@RequestMapping(value="/videos/{videoId}",method = RequestMethod.DELETE)
	public void deleteVideoByVideoId(@PathVariable("videoId") String id) {
      
		Long videoId = Long.parseLong(id);
		 loService.deleteVideoByVideoId(videoId);
	}
	
	
}
	


