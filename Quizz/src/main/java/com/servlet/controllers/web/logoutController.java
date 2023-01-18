package com.servlet.controllers.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.services.UserService;

@SuppressWarnings("serial")
@WebServlet("/dang-xuat")
public class logoutController extends HttpServlet{
	UserService userService = new UserService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			session.removeAttribute("userLogin");
			resp.sendRedirect(req.getContextPath()+"/dang-nhap");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
