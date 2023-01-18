package com.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.common.DBConnection;
import com.servlet.model.QuizzModel;

public class QuizzDao {
	public List<QuizzModel> selAllQuizz() throws Exception {
		Connection conn = null;
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from quizzes";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuizzModel quizzModel = new QuizzModel();
				quizzModel.setId(resultSet.getInt("id"));
				quizzModel.setUserId(resultSet.getInt("userId"));
				quizzModel.setImage(resultSet.getString("image"));
				quizzModel.setName(resultSet.getString("name"));
				quizzModel.setDescription(resultSet.getString("description"));
				listQuizz.add(quizzModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listQuizz;
	}
	public List<QuizzModel> selAllQuizz(int pageIndex, int pageSize) throws Exception {
		Connection conn = null;
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from quizzes limit ?, ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, pageIndex);
			preparedStatement.setInt(2, pageSize);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuizzModel quizzModel = new QuizzModel();
				quizzModel.setId(resultSet.getInt("id"));
				quizzModel.setUserId(resultSet.getInt("userId"));
				quizzModel.setImage(resultSet.getString("image"));
				quizzModel.setName(resultSet.getString("name"));
				quizzModel.setDescription(resultSet.getString("description"));
				listQuizz.add(quizzModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listQuizz;
	}
	public QuizzModel selQuizzById(int id) throws Exception {
		Connection conn = null;
		QuizzModel quizzModel = new QuizzModel();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from quizzes where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				quizzModel.setId(resultSet.getInt("id"));
				quizzModel.setUserId(resultSet.getInt("userId"));
				quizzModel.setImage(resultSet.getString("image"));
				quizzModel.setName(resultSet.getString("name"));
				quizzModel.setDescription(resultSet.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return quizzModel;
	}
	public List<QuizzModel> selAllQuizzByUserId(int userId) throws Exception {
		Connection conn = null;
		List<QuizzModel> listQuizz = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from quizzes where userId=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QuizzModel quizzModel = new QuizzModel();
				quizzModel.setId(resultSet.getInt("id"));
				quizzModel.setUserId(resultSet.getInt("userId"));
				quizzModel.setImage(resultSet.getString("image"));
				quizzModel.setName(resultSet.getString("name"));
				quizzModel.setDescription(resultSet.getString("description"));
				listQuizz.add(quizzModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listQuizz;
	}
	public int selCountQuizz() throws Exception {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select count(id) as count from quizzes";
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
	public void insertQuizz(QuizzModel quizzNew) throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "insert into quizzes(name, description, image) values (?, ?, ?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, quizzNew.getName());
			preparedStatement.setString(2, quizzNew.getDescription());
			preparedStatement.setString(3, quizzNew.getImage());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
	public void updateQuizz(QuizzModel quizzNew)throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "update quizzes set name=?, description=?, image=? where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, quizzNew.getName());
			preparedStatement.setString(2, quizzNew.getDescription());
			preparedStatement.setString(3, quizzNew.getImage());
			preparedStatement.setInt(4, quizzNew.getId());
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
	public void deleteQuizzById(int quizzId) throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.connectionDB();
			conn.setAutoCommit(false);
			String sql = "delete from quizzes where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, quizzId);
			preparedStatement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			DBConnection.rollbackTransaction(conn);
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
	}
}
