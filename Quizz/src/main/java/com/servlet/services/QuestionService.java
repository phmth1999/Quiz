package com.servlet.services;

import java.util.ArrayList;
import java.util.List;

import com.servlet.dao.AnswerDao;
import com.servlet.dao.QuestionDao;
import com.servlet.model.AnswerModel;
import com.servlet.model.QuestionModel;

public class QuestionService {
	QuestionDao questionDao = new QuestionDao();
	AnswerDao answerDao = new AnswerDao();
	
	public List<QuestionModel> getAllQuestionByQuizzId(int quizzId) throws Exception{
		List<QuestionModel> listQuestion = new ArrayList<>();
		try {
			listQuestion = questionDao.selAllQuestionByQuizzId(quizzId);
			for (QuestionModel questionModel : listQuestion) {
				List<AnswerModel> listAnswer = answerDao.selAllAnswerByQuestionId(questionModel.getId());
				questionModel.setListAnswer(listAnswer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listQuestion;
	}
	public QuestionModel getQuestionById(int id) throws Exception{
		QuestionModel question = new QuestionModel();
		try {
			question = questionDao.selQuestionById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	public int countQuestion() throws Exception{
		int count = 0;
		try {
			count = questionDao.selCountQuestion();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public int addQuestion(QuestionModel questionModel) throws Exception{
		int questionId = 0;
		try {
			questionId = questionDao.insertQuestion(questionModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questionId;
	}
	public void editQuestion(QuestionModel questionModel) throws Exception{
		try {
			questionDao.updateQuestion(questionModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
