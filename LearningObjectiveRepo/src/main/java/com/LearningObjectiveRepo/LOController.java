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

import com.LearningObjectiveRepo.video.Video;
import com.LearningObjectiveRepo.video.VideoIdFromURL;

@RestController
@RequestMapping(value = "/api")
public class LOController {

	@Autowired
	private LOService loService;

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

	@RequestMapping(value = "/videos", method = RequestMethod.POST)
	public void createVideo(@RequestBody video v) {
		String url = v.getUrl();
		loService.createVideo(url);

	}

	@RequestMapping(value = "/los/video/{sourceId}", method = RequestMethod.GET)

	public @ResponseBody List<LearningObjective> readLoBySourceId(@PathVariable String sourceId) {

		return loService.readLoBySourceId(sourceId);
	}

	@RequestMapping(value = "/los/video/{source}/{sourceId}", method = RequestMethod.GET)

	public @ResponseBody List<LearningObjective> readLoBySource(@PathVariable String source,
			@PathVariable String sourceId) {

		return loService.readLoBySource(source, sourceId);
	}

	@RequestMapping(value = "/los/{loId}", method = RequestMethod.GET)
	public @ResponseBody LearningObjective readLoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		return loService.readLoByLoId(loId);
	}

	@RequestMapping(value = "/videos/lo/{loId}", method = RequestMethod.GET)

	public @ResponseBody List<Video> readVideoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		return loService.readVideoByLoId(loId);
	}

	@RequestMapping(value = "/videos/{videoId}", method = RequestMethod.GET)
	public @ResponseBody Video readVideoByVideoId(@PathVariable("videoId") String vId) {

		Long videoId = Long.parseLong(vId);
		return loService.readVideoByVideoId(videoId);
	}
	
	@RequestMapping(value="/los/{loId}",method = RequestMethod.PUT)
	public void updateLoByLoId(@RequestBody Lo lo ,@PathVariable("loId") String id) {
      
		Long loId = Long.parseLong(id);
		String lobj=lo.getLo();
		 loService.updateLoByLoId(loId,lobj);
	}
	@RequestMapping(value="/videos/{videoId}",method = RequestMethod.PUT)
	public void updateVideoByVideoId(@RequestBody video v ,@PathVariable("videoId") String id) {
      
		Long videoId = Long.parseLong(id);
		String url=v.getUrl();
		 loService.updateVideoByVideoId(videoId,url);
	}
	@RequestMapping(value="/los/{loId}",method = RequestMethod.DELETE)
	public void deleteLoByLoId(@PathVariable("loId") String id) {
      
		Long loId = Long.parseLong(id);
		System.out.println(loId);
		 loService.deleteLoByLoId(loId);
	}
	@RequestMapping(value="/videos/{videoId}",method = RequestMethod.DELETE)
	public void deleteVideoByVideoId(@PathVariable("videoId") String id) {
      
		Long videoId = Long.parseLong(id);
		 loService.deleteVideoByVideoId(videoId);
	}
	

}
