package com.LearningObjectiveRepo.video;

import java.util.ArrayList;
import com.LearningObjectiveRepo.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.CascadeType;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
//import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id")
	private long videoId;

	@Column(name = "source")
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "source_id")
	private String sourceId;

	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="video_lo",joinColumns= {@JoinColumn(name="video_id")},inverseJoinColumns = {@JoinColumn(name="lo_id")}
	)
	private List<LearningObjective> lo = new ArrayList<LearningObjective>();

	public Video() {
	}

	public Video(String source, String sourceId) {
		super();
		this.source = source;
		this.sourceId = sourceId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public @JsonIgnore List<LearningObjective> getLo() {
		return lo;
	}

	public void setLo(List<LearningObjective> lo) {
		this.lo = lo;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

}
