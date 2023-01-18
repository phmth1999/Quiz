package com.servlet.controllers.admin.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.model.QuestionModel;
import com.servlet.services.QuestionService;

@SuppressWarnings("serial")
@WebServlet("/api/question")
public class questionApi extends HttpServlet{
	private static final Gson GSON = new GsonBuilder().create();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		QuestionService questionService = new QuestionService();
		try {
			if(req.getParameter("quizzId") != null && !req.getParameter("quizzId").isEmpty()) {
				int quizzId = Integer.parseInt(req.getParameter("quizzId").toString());
				List<QuestionModel> listQuestion = questionService.getAllQuestionByQuizzId(quizzId);
				
				resp.setStatus(200);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().println(GSON.toJson(listQuestion));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
