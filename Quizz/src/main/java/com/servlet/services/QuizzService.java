package com.servlet.services;

import java.util.ArrayList;
import java.util.List;

import com.servlet.dao.QuizzDao;
import com.servlet.model.QuizzModel;

public class QuizzService {
	QuizzDao quizzDao = new QuizzDao();

	public List<QuizzModel> getAllQuizz() throws Exception {
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			listQuizz = quizzDao.selAllQuizz();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listQuizz;
	}
	public List<QuizzModel> getAllQuizz(int pageIndex, int pageSize) throws Exception {
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			listQuizz = quizzDao.selAllQuizz(pageIndex, pageSize );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listQuizz;
	}
	public QuizzModel getQuizzById(int id) throws Exception{
		QuizzModel quizzModel = new QuizzModel();
		try {
			quizzModel = quizzDao.selQuizzById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizzModel;
	}
	public List<QuizzModel> getQuizzByUserId(int userId) throws Exception{
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			listQuizz = quizzDao.selAllQuizzByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listQuizz;
	}
	public int countQuizz() throws Exception{
		int count = 0;
		try {
			count = quizzDao.selCountQuizz();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public void addQuizz(QuizzModel quizzNew) throws Exception{
		try {
			quizzDao.insertQuizz(quizzNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void editQuizz(QuizzModel quizzNew) throws Exception{
		try {
			quizzDao.updateQuizz(quizzNew);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteQuizzById(int quizzId) throws Exception{
		try {
			quizzDao.deleteQuizzById(quizzId);;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
