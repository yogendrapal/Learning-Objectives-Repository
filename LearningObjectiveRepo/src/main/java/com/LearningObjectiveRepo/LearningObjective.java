package com.LearningObjectiveRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.LearningObjectiveRepo.category.Category;
import com.LearningObjectiveRepo.taxonomy.Taxonomy;
import com.LearningObjectiveRepo.type.Type;
import com.LearningObjectiveRepo.video.Video;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LearningObjective {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lo_id")
	private long loId;

	@Column(name = "lo_objective")
	private String lObjective;

	/*
	 * Many to Many relationship between Lo and video
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "video_lo", joinColumns = { @JoinColumn(name = "lo_id") }, inverseJoinColumns = {
			@JoinColumn(name = "video_id") })
	private List<Video> video = new ArrayList<Video>();

	/*
	 * Many to One relationship between Lo and taxonomy
	 */
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "taxo_id")
	private Taxonomy taxonomy;

	/*
	 * One learning objective can have many child
	 */
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "lo_parent")
	private LearningObjective lo_parent;
	@OneToMany(mappedBy = "lo_parent")
	private List<LearningObjective> lo_child = new ArrayList<LearningObjective>();

	/*
	 * Many lo can have many siblings
	 */
	@ManyToMany
	@JoinTable(name = "lo_sibling", joinColumns = { @JoinColumn(name = "lo_id") }, inverseJoinColumns = {
			@JoinColumn(name = "sibling_id") })
	private List<LearningObjective> lobj = new ArrayList<LearningObjective>();
	@ManyToMany
	@JoinTable(name = "lo_sibling", joinColumns = { @JoinColumn(name = "sibling_id") }, inverseJoinColumns = {
			@JoinColumn(name = "lo_id") })
	private List<LearningObjective> lo_sibling = new ArrayList<LearningObjective>();

	/*
	 * Many to Many relationship between Lo and category(domain,field,subject,topic)
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "lo_category", joinColumns = { @JoinColumn(name = "lo_id") }, inverseJoinColumns = {
			@JoinColumn(name = "domainId"), @JoinColumn(name = "fieldId"), @JoinColumn(name = "subjectId"),
			@JoinColumn(name = "topicId") })
	private Set<Category> category = new HashSet<>();

	/*
	 * Many to Many relationship between Lo and type(taxonomy,level,verb)
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	@JoinTable(name = "lo_type", joinColumns = { @JoinColumn(name = "lo_id") }, inverseJoinColumns = {

			@JoinColumn(name = "levelId"), @JoinColumn(name = "taxoId"), @JoinColumn(name = "verbId") })
	private Set<Type> type = new HashSet<>();

	/*
	 * Getters and Setters
	 */
	public @JsonIgnore Set<Type> getType() {
		return type;
	}

	public void setType(Set<Type> type) {
		this.type = type;
	}

	public @JsonIgnore Taxonomy getTaxonomy() {
		return taxonomy;
	}

	public void setTaxonomy(Taxonomy taxonomy) {
		this.taxonomy = taxonomy;
	}

	public @JsonIgnore LearningObjective getLo_parent() {
		return lo_parent;
	}

	public void setLo_parent(LearningObjective lo_parent) {
		this.lo_parent = lo_parent;
	}

	public @JsonIgnore List<LearningObjective> getLo_child() {
		return lo_child;
	}

	public void setLo_child(List<LearningObjective> lo_child) {
		this.lo_child = lo_child;
	}

	public @JsonIgnore List<LearningObjective> getLobj() {
		return lobj;
	}

	public void setLobj(List<LearningObjective> lobj) {
		this.lobj = lobj;
	}

	public @JsonIgnore List<LearningObjective> getLo_sibling() {
		return lo_sibling;
	}

	public void setLo_sibling(List<LearningObjective> lo_sibling) {
		this.lo_sibling = lo_sibling;
	}

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

	public @JsonIgnore Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public @JsonIgnore List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	/*
	 * Constructors
	 */
	public LearningObjective() {
	}

	public LearningObjective(String lObjective) {
		super();
		this.lObjective = lObjective;
	}

}
