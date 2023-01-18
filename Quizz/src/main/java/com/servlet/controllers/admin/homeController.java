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
import com.servlet.services.AnswerService;
import com.servlet.services.QuestionService;
import com.servlet.services.QuizzService;
import com.servlet.services.UserService;

@SuppressWarnings("serial")
@WebServlet("/quan-tri/trang-chu")
public class homeController extends HttpServlet{
	UserService userService = new UserService();
	QuizzService quizzService = new QuizzService();
	QuestionService questionService = new QuestionService();
	AnswerService answerService = new AnswerService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if(userLogin != null) {
				if(userLogin.getRole().equals("admin")) {
					req.setAttribute("countUser", userService.countUser());
					req.setAttribute("countQuizz", quizzService.countQuizz());
					req.setAttribute("countQuestion", questionService.countQuestion());
					req.setAttribute("countAnswer", answerService.countUser());
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/index.jsp");
					rd.forward(req, resp);
				}else {
					session.setAttribute("errMessage", "Account not authentication!");
					resp.sendRedirect(req.getContextPath()+"/dang-nhap");
				}
			}else{
				resp.sendRedirect(req.getContextPath()+"/dang-nhap");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
