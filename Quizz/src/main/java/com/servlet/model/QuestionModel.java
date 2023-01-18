package com.servlet.model;

import java.util.List;

public class QuestionModel {
	private int id;
	private int quizzId;
	private String content;
	private String image;
	private List<AnswerModel> listAnswer;

	@Override
	public String toString() {
		return "QuestionModel [id=" + id + ", quizzId=" + quizzId + ", content=" + content + ", image=" + image
				+ ", listAnswer=" + listAnswer + "]";
	}

	public QuestionModel() {

	}

	public QuestionModel(int id, int quizzId, String content, String image, List<AnswerModel> listAnswer) {
		super();
		this.id = id;
		this.quizzId = quizzId;
		this.content = content;
		this.image = image;
		this.listAnswer = listAnswer;
	}

	public List<AnswerModel> getListAnswer() {
		return listAnswer;
	}

	public void setListAnswer(List<AnswerModel> listAnswer) {
		this.listAnswer = listAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuizzId() {
		return quizzId;
	}

	public void setQuizzId(int quizzId) {
		this.quizzId = quizzId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
