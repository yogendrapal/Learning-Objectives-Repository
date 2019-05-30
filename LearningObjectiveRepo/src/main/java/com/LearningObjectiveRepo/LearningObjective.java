package com.LearningObjectiveRepo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LearningObjective {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lo_id")
	private long loId;

	public LearningObjective() {
	}

	public LearningObjective(String lObjective) {
		super();
		this.lObjective = lObjective;
	}

	@Column(name = "lo_objective")
	private String lObjective;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "lo")
	private List<Video> video = new ArrayList<Video>();

	@OneToMany(mappedBy = "lo")
	private List<Taxonomy> taxonomy = new ArrayList<>();

	public long getLoId() {
		return loId;
	}

	public void setLoId(long loId) {
		this.loId = loId;
	}

	public String getlObjective() {
		return lObjective;
	}

	public void setlObjective(String lObjective) {
		this.lObjective = lObjective;
	}

	public @JsonIgnore List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

}
