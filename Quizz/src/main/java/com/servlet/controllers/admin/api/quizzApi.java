package com.servlet.controllers.admin.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.servlet.model.QuizzModel;
import com.servlet.services.QuizzService;

@SuppressWarnings("serial")
@WebServlet("/api/quizz")
public class quizzApi extends HttpServlet {
	private static final Gson GSON = new GsonBuilder().create();
	QuizzService quizzService = new QuizzService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int pageIndex = 0;
			int pageSize = 0;
			List<QuizzModel> listQuizz = new ArrayList<>();
			if(req.getParameter("pageIndex") != null && !req.getParameter("pageIndex").isEmpty()) {
				pageIndex = Integer.parseInt(req.getParameter("pageIndex").toString());
				pageSize = Integer.parseInt(req.getParameter("pageSize").toString());
				listQuizz = quizzService.getAllQuizz(pageIndex, pageSize);
			}else {
				listQuizz = quizzService.getAllQuizz();
			}
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(GSON.toJson(listQuizz));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
