package com.servlet.controllers.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.model.UserModel;
import com.servlet.services.UserService;

@SuppressWarnings("serial")
@WebServlet("/dang-nhap")
public class loginController extends HttpServlet {
	UserService userService = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			if (session.getAttribute("errMessage") != null && session.getAttribute("errMessage") != "") {
				req.setAttribute("errMessage", session.getAttribute("errMessage").toString());
				session.removeAttribute("errMessage");
			}
			RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			String userName = req.getParameter("username").toString();
			String password = req.getParameter("password").toString();
			req.setAttribute("userName", userName);
			req.setAttribute("password", password);
			if (session.getAttribute("userLogin") == null || session.getAttribute("userLogin") == "") {
				if (userService.checkUserNameExist(userName)) {
					if (userService.login(userName, password) != null) {
						UserModel userLogin = userService.login(userName, password);
						session.setAttribute("userLogin", userLogin);
						if (userService.isAdmin(userLogin.getRole())) {
							resp.sendRedirect(req.getContextPath() + "/quan-tri/trang-chu");
						} else if (userService.isUser(userLogin.getRole())) {
							resp.sendRedirect(req.getContextPath() + "/trang-chu");
						}
					} else {
						req.setAttribute("errMessage", "Password not correct!");
						RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
						rd.forward(req, resp);
					}
				} else {
					req.setAttribute("errMessage", "Username not exist!");
					RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
					rd.forward(req, resp);
				}
			} else {
				req.setAttribute("errMessage", "Please logout!");
				RequestDispatcher rd = req.getRequestDispatcher("/views/web/login.jsp");
				rd.forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
