package com.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.servlet.common.DBConnection;
import com.servlet.model.QuestionModel;

public class QuestionDao {
	public List<QuestionModel> selAllQuestion() throws Exception {
		Connection conn = null;
		List<QuestionModel> listQuestion = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from questions";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuestionModel questionModel = new QuestionModel();
				questionModel.setId(resultSet.getInt("id"));
				questionModel.setQuizzId(resultSet.getInt("quizzId"));
				questionModel.setImage(resultSet.getString("image"));
				questionModel.setContent(resultSet.getString("content"));
				listQuestion.add(questionModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listQuestion;
	}
	public QuestionModel selQuestionById(int id) throws Exception {
		Connection conn = null;
		QuestionModel questionModel = new QuestionModel();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from questions where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				questionModel.setId(resultSet.getInt("id"));
				questionModel.setQuizzId(resultSet.getInt("quizzId"));
				questionModel.setImage(resultSet.getString("image"));
				questionModel.setContent(resultSet.getString("content"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return questionModel;
	}
	public List<QuestionModel> selAllQuestionByQuizzId(int quizzId) throws Exception {
		Connection conn = null;
		List<QuestionModel> listQuestion = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from questions where quizzId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, quizzId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuestionModel questionModel = new QuestionModel();
				questionModel.setId(resultSet.getInt("id"));
				questionModel.setQuizzId(resultSet.getInt("quizzId"));
				questionModel.setImage(resultSet.getString("image"));
				questionModel.setContent(resultSet.getString("content"));
				listQuestion.add(questionModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listQuestion;
	}
	public int selCountQuestion() throws Exception {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select count(id) as count from questions";
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
	public int insertQuestion(QuestionModel questionModel) throws Exception {
		Connection conn = null;
		int questionId = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "insert into questions(quizzId, content, image) values (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS );
			preparedStatement.setInt(1, questionModel.getQuizzId());
			preparedStatement.setString(2, questionModel.getContent());
			preparedStatement.setString(3, questionModel.getImage());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				questionId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return questionId;
	}
	public void updateQuestion(QuestionModel questionModel) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			String sql = "update questions set content=?, image=? where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, questionModel.getContent());
			preparedStatement.setString(2, questionModel.getImage());
			preparedStatement.setInt(3, questionModel.getId());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
}
