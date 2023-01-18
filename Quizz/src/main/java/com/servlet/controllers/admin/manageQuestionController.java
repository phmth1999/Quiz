package com.servlet.controllers.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.servlet.model.AnswerModel;
import com.servlet.model.QuestionModel;
import com.servlet.model.QuizzModel;
import com.servlet.model.UserModel;
import com.servlet.services.AnswerService;
import com.servlet.services.QuestionService;
import com.servlet.services.QuizzService;

@SuppressWarnings("serial")
@WebServlet("/quan-tri/question")
public class manageQuestionController extends HttpServlet {
	QuizzService quizzSevice = new QuizzService();
	QuestionService questionService = new QuestionService();
	AnswerService answerService = new AnswerService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if (userLogin != null) {
				if (userLogin.getRole().equals("admin")) {
					List<QuizzModel> listQuizz = quizzSevice.getAllQuizz();
					req.setAttribute("listQuizz", listQuizz);
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/manageQuestion.jsp");
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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String quizzId = req.getParameter("quizzId").toString();
			String[] question = req.getParameterValues("question");
			String[] countAnswer = req.getParameterValues("count-answer");
			String[] answer = req.getParameterValues("content-answer");
			String[] questionIdParameter = req.getParameterValues("questionId");
			String[] answerIdParameter = req.getParameterValues("answerId");
			String[] checkboxAnswer = req.getParameterValues("checkboxAnswer");
			int count = 0;
			int k = 0;
			for (int i = 0; i < question.length; i++) {
				QuestionModel questionModel = new QuestionModel();
				questionModel.setQuizzId(Integer.parseInt(quizzId));
				questionModel.setContent(question[i]);
				int questionId = Integer.parseInt(questionIdParameter[i]);
				if(Integer.parseInt(questionIdParameter[i]) == 0) {
					questionId = questionService.addQuestion(questionModel);
				}else {
					questionModel.setId(questionId);
					questionService.editQuestion(questionModel);
				}
				
				for (int j = 0; j < Integer.parseInt(countAnswer[i]); j++) {
					AnswerModel answerModel = new AnswerModel();
					answerModel.setQuestionId(questionId);
					answerModel.setContent(answer[j+count]);
					answerModel.setStatus("false");
					if(k < checkboxAnswer.length) {
						int vitriQ = Integer.parseInt(checkboxAnswer[k].split(":")[1]);
						int vitriA = Integer.parseInt(checkboxAnswer[k].split(":")[0]);
						if(vitriA==j && vitriQ==i) {
							answerModel.setStatus("true");
							k++;
						}
					}
					
					if(Integer.parseInt(answerIdParameter[j+count]) == 0) {
						answerService.addAnswer(answerModel);
					}else {
						answerModel.setId(Integer.parseInt(answerIdParameter[j+count]));
						answerService.editAnswer(answerModel);
					}
				}
				count += Integer.parseInt(countAnswer[i]);
			}
		
			resp.sendRedirect(req.getContextPath() + "/quan-tri/question");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
