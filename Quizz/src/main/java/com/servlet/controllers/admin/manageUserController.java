package com.servlet.controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.model.UserModel;

@SuppressWarnings("serial")
@WebServlet("/quan-tri/user")
public class manageUserController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if (userLogin != null) {
				if (userLogin.getRole().equals("admin")) {
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/manageUser.jsp");
					rd.forward(req, resp);
				} else {
					session.setAttribute("errMessage", "Account not authentication!");
					resp.sendRedirect(req.getContextPath() + "/dang-nhap");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
