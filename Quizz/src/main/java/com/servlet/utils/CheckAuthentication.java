package com.servlet.utils;

import javax.servlet.http.HttpSession;

import com.servlet.model.UserModel;

public class CheckAuthentication {
public static String checkAuthentication(HttpSession session) {
	String res = "";
	try {
		UserModel userLogin = (UserModel) session.getAttribute("userLogin");
		if (userLogin != null) {
			if (userLogin.getRole().equals("admin")) {
				res = "ok";
			} else {
				res = "Account not authentication!";
			}
		} 
	} catch (Exception e) {
		e.printStackTrace();
	}
	return res;
}
}
