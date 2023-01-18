package com.servlet.services;

import com.servlet.dao.AnswerDao;
import com.servlet.model.AnswerModel;

public class AnswerService {
	AnswerDao answerDao = new AnswerDao();
	public int countUser() throws Exception{
		int count = 0;
		try {
			count = answerDao.selCountAnswer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public void addAnswer(AnswerModel answerModel) throws Exception{
		try {
			answerDao.insertAnswer(answerModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editAnswer(AnswerModel answerModel) throws Exception{
		try {
			answerDao.updateAnswer(answerModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
