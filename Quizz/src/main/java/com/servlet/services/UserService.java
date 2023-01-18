package com.servlet.services;

import com.servlet.dao.UserDao;
import com.servlet.model.UserModel;

public class UserService {
	UserDao userDao = new UserDao();
	public int countUser() throws Exception{
		int count = 0;
		try {
			count = userDao.selCountUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public boolean checkUserNameExist(String userName) throws Exception{
		boolean valid = false;
		try {
			if(userDao.selCheckUserNameExist(userName)) {
				valid = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;
	}
	public UserModel login(String userName, String password) throws Exception{
		UserModel userModel = null;
		try {
			if(checkUserNameExist(userName)) {
				if(userDao.selCheckLogin(userName, password) != null) {
					userModel = userDao.selCheckLogin(userName, password);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userModel;
	}
	public boolean isAdmin(String role) throws Exception{
		boolean valid = false;
		if(role.equals("admin")) {
			valid = true;
		}
		return valid;
	}
	public boolean isUser(String role) throws Exception{
		boolean valid = false;
		if(role.equals("user")) {
			valid = true;
		}
		return valid;
	}

}
