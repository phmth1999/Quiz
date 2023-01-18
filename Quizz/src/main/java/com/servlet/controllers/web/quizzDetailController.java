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

import com.servlet.model.QuestionModel;
import com.servlet.model.UserModel;
import com.servlet.services.QuestionService;

@SuppressWarnings("serial")
@WebServlet("/quizz-detail")
public class quizzDetailController extends HttpServlet {
	QuestionService questionService = new QuestionService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if (userLogin != null) {
				if (req.getParameter("quizzId").toString() != null) {
					int quizzId = Integer.parseInt(req.getParameter("quizzId").toString());
					List<QuestionModel> listQuestion = questionService.getAllQuestionByQuizzId(quizzId);
					req.setAttribute("listQuestion", listQuestion);
					RequestDispatcher rd = req.getRequestDispatcher("/views/web/quizzDetail.jsp");
					rd.forward(req, resp);
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
