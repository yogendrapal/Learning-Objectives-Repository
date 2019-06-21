package com.LearningObjectiveRepo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.LearningObjectiveRepo.ExceptionHandling.ResourceNotFoundException;
import com.LearningObjectiveRepo.UserAccounts.Message;
import com.LearningObjectiveRepo.video.Video;
import com.LearningObjectiveRepo.video.VideoIdFromURL;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping(value = "/api/secured")
public class LOController {

	@Autowired
	private LOService loService;

	/**
	 * Create videos and their corresponding learning objectives.
	 *
	 * @param video url and learning objective in json format
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/videos/los", method = RequestMethod.POST)
	public Message createVideo_Lo(@RequestBody ReqFormat rf) {
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
		Message m=new Message();
		m.setMessage("Video and it's corresponding learning objective submitted successfully");
		return m;

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
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los", method = RequestMethod.POST)

	public Message createLO(@RequestBody Lo lobj) {
		String lo = lobj.getLo();
		loService.createLo(lo);
		Message m=new Message();
		m.setMessage("Learning objective submitted successfully");
		return m;

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

	@PreAuthorize("hasAnyRole('Admin','Reviewer')")
	@RequestMapping(value = "/videos", method = RequestMethod.POST)
	public Message createVideo(@RequestBody video v) {
		String url = v.getUrl();
		loService.createVideo(url);
		Message m=new Message();
		m.setMessage("Video submitted successfully");
		return m;

	}

	/**
	 * Gets LearningObjectives by Video.sourceId.
	 *
	 * @return the list of Learning Objectives by video.sourceId
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/video/{sourceId}", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> readLoBySourceId(@PathVariable String sourceId) {

		List<LearningObjective> lo = loService.readLoBySourceId(sourceId);
		if (lo == null)
			throw new ResourceNotFoundException("Source id not found - " + sourceId);
		return lo;
	}

	/**
	 * Gets LearningObjectives by Video.source and video.sourceId.
	 *
	 * @return the list of Learning Objectives
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/video/{source}/{sourceId}", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> readLoBySource(@PathVariable String source,
			@PathVariable String sourceId) {

		List<LearningObjective> lo = loService.readLoBySource(source, sourceId);
		if (lo == null)
			throw new ResourceNotFoundException("Source id not found - " + sourceId);
		return lo;

	}

	/**
	 * Get LearningObjectives by LoId.
	 *
	 * @return the Learning Objective for the particular LoId
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.GET)
	public @ResponseBody LearningObjective readLoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		LearningObjective lo = loService.readLoByLoId(loId);
		if (lo == null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		return lo;
	}
	@RequestMapping(value = "/los", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> readAllLo() {

		List<LearningObjective> lo = loService.readAllLo();
		if (lo == null)
			throw new ResourceNotFoundException("Learning Objective not found");
		return lo;
	}
	/**
	 * Get videos by particular learning objective Id.
	 *
	 * @return the list of videos
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/videos/lo/{loId}", method = RequestMethod.GET)
	public @ResponseBody List<Video> readVideoByLoId(@PathVariable("loId") String lOId) {

		Long loId = Long.parseLong(lOId);
		List<Video> v = loService.readVideoByLoId(loId);
		if (v == null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		return v;
	}

	/**
	 * Get video for a particular videoId
	 *
	 * @return video
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/videos/{videoId}", method = RequestMethod.GET)
	public @ResponseBody Video readVideoByVideoId(@PathVariable("videoId") String vId) {

		Long videoId = Long.parseLong(vId);
		Video v = loService.readVideoByVideoId(videoId);
		if (v == null)
			throw new ResourceNotFoundException("video id not found - " + vId);
		return v;
	}

	/**
	 * Update learning objective by LoId.
	 *
	 * @param learning objective Id passed as a part of url
	 * @param learning objective in json format passed in request body
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.PUT)
	public Message updateLoByLoId(@RequestBody Lo lo, @PathVariable("loId") String id) {

		Long loId = Long.parseLong(id);
		String lobj = lo.getLo();
		loService.updateLoByLoId(loId, lobj);
		Message m=new Message();
		m.setMessage("Learning objective updated successfully");
		return m;
	}

	/**
	 * Update video by videoId
	 *
	 * @param videoId passed as a part of url
	 * @param video   url in json format passed in request body
	 */

	@RequestMapping(value = "/videos/{videoId}", method = RequestMethod.PUT)
	public Message updateVideoByVideoId(@RequestBody video v, @PathVariable("videoId") String id) {

		Long videoId = Long.parseLong(id);
		String url = v.getUrl();
		loService.updateVideoByVideoId(videoId, url);
		Message m=new Message();
		m.setMessage("Video updated successfully");
		return m;
	}

	/**
	 * Delete learning objective by LoId.
	 *
	 * @param loId passed as a part of url
	 */
	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/los/{loId}", method = RequestMethod.DELETE)
	public Message deleteLoByLoId(@PathVariable("loId") String id) {

		Long loId = Long.parseLong(id);
		Boolean b = loService.deleteLoByLoId(loId);
		if (b)
			{
			Message m=new Message();
			m.setMessage("Deleted learning objective having id " + loId);
			return m;
			}
		else
			throw new ResourceNotFoundException("learning objective id not found - " + loId);

	}

	/**
	 * Delete video by videoId.
	 *
	 * @param videoId passed as a part of url
	 */

	@RequestMapping(value = "/videos/{videoId}", method = RequestMethod.DELETE)
	public Message deleteVideoByVideoId(@PathVariable("videoId") String id) {

		Long videoId = Long.parseLong(id);
		Boolean b = loService.deleteVideoByVideoId(videoId);
		if (b)
			{
			Message m=new Message();
			m.setMessage("Deleted video having id " + videoId);
			return m;
			}
		else
			throw new ResourceNotFoundException("video id not found - " + videoId);
	}

	/**
	 * Create learning objective child for a given learning objective id and child
	 * id.
	 *
	 * @param parent learning objective id lId and child learning objective Id chId
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}/children/{childId}", method = RequestMethod.POST)
	public Message setLoChild(@PathVariable("loId") String lId, @PathVariable("childId") String chId) {
		Long loId = Long.parseLong(lId);
		Long childId = Long.parseLong(chId);
		loService.setLoChild(loId, childId);
	
		Message m=new Message();
		m.setMessage("Child learning objective added successfully");
		return m;
		}
	/**
	 * Get list of learning objective child for a given learning objective id.
	 *
	 * @param parent learning objective id lId
	 */
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}/children", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> getLoChild(@PathVariable("loId") String lId) {
		Long loId = Long.parseLong(lId);
		List<LearningObjective> lo = loService.getLoChild(loId);
		System.out.println(lo);
		System.out.println(lo.size());
		if (lo.isEmpty() || lo == null)
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		else
			return lo;

	}
	/**
	 * Delete learning objective child for a given learning objective id.
	 *
	 * @param parent learning objective id lId
	 */

	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/los/{loId}/children", method = RequestMethod.DELETE)
	public Message deleteChildrenByLoId(@PathVariable("loId") String lid) {

		Long loId = Long.parseLong(lid);
		LearningObjective lo = loService.deleteChildrenByLoId(loId);
		if (lo != null)
			{
			Message m=new Message();
			m.setMessage("Deleted children for parent having id " + loId);
			return m;
			}
		else
			throw new ResourceNotFoundException("Deletion of children is not possible for the given id - " + loId);
	}
	/**
	 * Delete learning objective child for a given child learning objective id.
	 *
	 * @param child learning objective id chId
	 */


	@PreAuthorize("hasAnyRole('Admin')")
	@RequestMapping(value = "/los/children/{cId}", method = RequestMethod.DELETE)
	public Message deleteChildByLoId(@PathVariable("cId") String chid) {

		Long childId = Long.parseLong(chid);
		LearningObjective lo = loService.deleteChildByLoId(childId);
		if (lo != null)
			{
			Message m=new Message();
			m.setMessage("child having id " + childId + " is deleted");
			return m;
			}
		else
			throw new ResourceNotFoundException("Deletion not possible for the given id - " + childId);
	}
	/**
	 * Create learning objective sibling for a given learning objective id and
	 * sibling id.
	 *
	 * @param learning objective id lId and sibling id sId
	 */

	
	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}/sibling/{sibId}", method = RequestMethod.POST)
	public Message setLoSibling(@PathVariable("loId") String lId, @PathVariable("sibId") String sId) {
		Long loId = Long.parseLong(lId);
		Long sibId = Long.parseLong(sId);
		if (loId != sibId) {
			Boolean b = loService.setLoSibling(loId, sibId);
			if (b)
				{
				Message m=new Message();
				m.setMessage("Sibling learning objective added successfully");
				return m;
				}
			else
				throw new ResourceNotFoundException(" Given id is not present . ");
		}

		throw new ResourceNotFoundException(" Both ids are same . ");
	}

	/**
	 * Get learning objective sibling for a given learning objective id.
	 *
	 * @param learning objective id lId
	 */

	@PreAuthorize("hasAnyRole('Admin','Reviewer','Creator')")
	@RequestMapping(value = "/los/{loId}/sibling", method = RequestMethod.GET)
	public @ResponseBody List<LearningObjective> getLoSibling(@PathVariable("loId") String lId) {
		Long loId = Long.parseLong(lId);
		List<LearningObjective> lo = loService.getLoSibling(loId);
		if (lo == null || lo.isEmpty()) {
			throw new ResourceNotFoundException("Learning Objective id not found - " + loId);
		} else
			return lo;
	}

	/**
	 * Create learning objective sibling for a given learning objective id.
	 *
	 * @param learning objective id lId
	 */
	@RequestMapping(value = "/los/{loId}/sibling", method = RequestMethod.DELETE)
	public Message deleteSiblingByLoId(@PathVariable("loId") String lId) {

		Long loId = Long.parseLong(lId);
		LearningObjective lo = loService.deleteSiblingByLoId(loId);
		if (lo != null)
			{
			Message m=new Message();
			m.setMessage(" Siblings of learning objective having id  " + loId + " are deleted");
			return m;
			}
		else
			throw new ResourceNotFoundException("Deletion not possible for the given id - " + loId);
	}

	/**
	 * Delete mapping between learning objective sibling for given ids.
	 *
	 * @param learning objective id lId and sibling id sId
	 */

	@RequestMapping(value = "/los/{loId}/sibling/{sId}", method = RequestMethod.DELETE)
	public Message deleteSiblingBySiblingId(@PathVariable("loId") String lId, @PathVariable("sId") String sId) {

		Long loId = Long.parseLong(lId);
		Long sibId = Long.parseLong(sId);
		Boolean b = loService.deleteSiblingBySiblingId(loId, sibId);
		if (b) {
			Message m=new Message();
			m.setMessage("Sibling deleted");
			return m;
		} else
			throw new ResourceNotFoundException("Deletion not possible");
	}
}
