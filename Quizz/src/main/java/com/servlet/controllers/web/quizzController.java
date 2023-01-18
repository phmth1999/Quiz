package com.servlet.controllers.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.model.QuizzModel;
import com.servlet.model.UserModel;
import com.servlet.services.QuizzService;

@SuppressWarnings("serial")
@WebServlet("/quizz")
public class quizzController extends HttpServlet{
	QuizzService quizzService = new QuizzService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if(userLogin != null) {
				List<QuizzModel> listQuizz = quizzService.getQuizzByUserId(userLogin.getId());
				req.setAttribute("listQuizz", listQuizz);
				RequestDispatcher rd = req.getRequestDispatcher("/views/web/quizz.jsp");
				rd.forward(req, resp);
			}else {
				resp.sendRedirect(req.getContextPath()+"/dang-nhap");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
