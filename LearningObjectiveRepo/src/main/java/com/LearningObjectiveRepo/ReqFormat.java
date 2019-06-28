package com.LearningObjectiveRepo;

/*
 * Additional class created to receive video_url and learning objective in a single json object
 */
public class ReqFormat {

	private String url;
	private String lo;

	public String geturl() {
		return url;
	}

	public void seturl(String url) {
		this.url = url;
	}

	public String getlo() {
		return lo;
	}

	public void setlo(String lo) {
		this.lo = lo;
	}

}
