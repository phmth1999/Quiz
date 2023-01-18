package com.servlet.model;

public class AnswerModel {
	private int id;
	private int questionId;
	private String content;
	private String status;

	public AnswerModel() {

	}

	public AnswerModel(int id, int questionId, String content, String status) {
		this.id = id;
		this.questionId = questionId;
		this.content = content;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
