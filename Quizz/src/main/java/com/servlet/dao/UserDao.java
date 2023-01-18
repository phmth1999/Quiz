package com.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.servlet.common.DBConnection;
import com.servlet.model.UserModel;

public class UserDao {
	public boolean selCheckUserNameExist(String userName) throws Exception {
		Connection conn = null;
		boolean valid = false;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from users where userName=BINARY ?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return valid;
	}
	public UserModel selCheckLogin(String userName, String password) throws Exception {
		Connection conn = null;
		UserModel userModel = null;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from users where userName=BINARY ? and password=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userModel = new UserModel(); 
				userModel.setId(resultSet.getInt("id"));
				userModel.setUserName(resultSet.getString("userName"));
				userModel.setPassword(resultSet.getString("password"));
				userModel.setFirstName(resultSet.getString("firstName"));
				userModel.setLastName(resultSet.getString("lastName"));
				userModel.setEmail(resultSet.getString("email"));
				userModel.setPhone(resultSet.getString("phone"));
				userModel.setAddress(resultSet.getString("address"));
				userModel.setRole(resultSet.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return userModel;
	}
	public List<UserModel> selAllUser() throws Exception {
		Connection conn = null;
		List<UserModel> listUser = new ArrayList<>();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from users";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserModel userModel = new UserModel();
				userModel.setId(resultSet.getInt("id"));
				userModel.setUserName(resultSet.getString("userName"));
				userModel.setPassword(resultSet.getString("password"));
				userModel.setFirstName(resultSet.getString("firstName"));
				userModel.setLastName(resultSet.getString("lastName"));
				userModel.setEmail(resultSet.getString("email"));
				userModel.setPhone(resultSet.getString("phone"));
				userModel.setAddress(resultSet.getString("address"));
				userModel.setRole(resultSet.getString("role"));
				listUser.add(userModel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return listUser;
	}
	public UserModel selUserById(int id) throws Exception {
		Connection conn = null;
		UserModel userModel = new UserModel();
		try {
			conn = DBConnection.connectionDB();
			String sql = "select * from users where id=?";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				userModel.setId(resultSet.getInt("id"));
				userModel.setUserName(resultSet.getString("userName"));
				userModel.setPassword(resultSet.getString("password"));
				userModel.setFirstName(resultSet.getString("firstName"));
				userModel.setLastName(resultSet.getString("lastName"));
				userModel.setEmail(resultSet.getString("email"));
				userModel.setPhone(resultSet.getString("phone"));
				userModel.setAddress(resultSet.getString("address"));
				userModel.setRole(resultSet.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnectionDB(conn);;
		}
		return userModel;
	}
	public int selCountUser() throws Exception {
		Connection conn = null;
		int count = 0;
		try {
			conn = DBConnection.connectionDB();
			String sql = "select count(id) as count from users";
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
}
