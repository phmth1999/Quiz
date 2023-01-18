package com.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.common.DBConnection;
import com.servlet.model.AnswerModel;

public class AnswerDao {
	public List<AnswerModel> selAllAnswer() throws Exception {
		Connection conn = null;
		List<AnswerModel> listAnswer = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from answers";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AnswerModel answerModel = new AnswerModel();
				answerModel.setId(resultSet.getInt("id"));
				answerModel.setQuestionId(resultSet.getInt("questionId"));
				answerModel.setContent(resultSet.getString("content"));
				answerModel.setStatus(resultSet.getString("status"));
				listAnswer.add(answerModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listAnswer;
	}
	public AnswerModel selAnswerById(int id) throws Exception {
		Connection conn = null;
		AnswerModel answerModel = new AnswerModel();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from answers where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				answerModel.setId(resultSet.getInt("id"));
				answerModel.setQuestionId(resultSet.getInt("questionId"));
				answerModel.setContent(resultSet.getString("content"));
				answerModel.setStatus(resultSet.getString("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return answerModel;
	}
	public List<AnswerModel> selAllAnswerByQuestionId(int questionId) throws Exception {
		Connection conn = null;
		List<AnswerModel> listAnswer = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from answers where questionId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, questionId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AnswerModel answerModel = new AnswerModel();
				answerModel.setId(resultSet.getInt("id"));
				answerModel.setQuestionId(resultSet.getInt("questionId"));
				answerModel.setContent(resultSet.getString("content"));
				answerModel.setStatus(resultSet.getString("status"));
				listAnswer.add(answerModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listAnswer;
	}
	public int selCountAnswer() throws Exception {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select count(id) as count from answers";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return count;
	}
	public void insertAnswer(AnswerModel answerModel) throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			String sql = "insert into answers(questionId, content, status) values (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, answerModel.getQuestionId());
			preparedStatement.setString(2, answerModel.getContent());
			preparedStatement.setString(3, answerModel.getStatus());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
	public void updateAnswer(AnswerModel answerModel) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			String sql = "update answers set content=?, status=? where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, answerModel.getContent());
			preparedStatement.setString(2, answerModel.getStatus());
			preparedStatement.setInt(3, answerModel.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
}
