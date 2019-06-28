package com.LearningObjectiveRepo.UserAccounts;

/*
 * Class to return response status/messages as json object
 */
public class Message {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Message(String message) {
		super();
		this.message = message;
	}

	public Message() {
		super();
	}

}
