package com.servlet.controllers.admin;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.servlet.model.QuizzModel;
import com.servlet.model.UserModel;
import com.servlet.services.QuizzService;

@SuppressWarnings("serial")
@WebServlet("/quan-tri/quizz")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class manageQuizzController extends HttpServlet {
	QuizzService quizzService = new QuizzService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			UserModel userLogin = (UserModel) session.getAttribute("userLogin");
			if (userLogin != null) {
				if (userLogin.getRole().equals("admin")) {
					if(req.getParameter("deleteQuizzId") != null && !req.getParameter("deleteQuizzId").isEmpty()) {
						int id = Integer.parseInt(req.getParameter("deleteQuizzId").toString());
						int currentPage = Integer.parseInt(req.getParameter("currentPage").toString());
						quizzService.deleteQuizzById(id);
						req.setAttribute("delete", "delete");
						req.setAttribute("currentPage", currentPage);
					}
					if(req.getParameter("quizzId") != null && !req.getParameter("quizzId").isEmpty()) {
						int id = Integer.parseInt(req.getParameter("quizzId").toString());
						QuizzModel quizzModel = quizzService.getQuizzById(id);
						req.setAttribute("quizzModel", quizzModel);
						req.setAttribute("edit", "edit");
					}
					RequestDispatcher rd = req.getRequestDispatcher("/views/admin/manageQuizz.jsp");
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
			if(req.getParameter("add") != null) {
				Part filePart = req.getPart("image");
				String fileName = getFileName(filePart);
				if (!fileName.isEmpty()) {
					for (Part part : req.getParts()) {
						part.write(getFolderUpload() + "//" + fileName);
					}
				}
				String nameQuizz = req.getParameter("name").toString();
				String descriptionQuizz = req.getParameter("description").toString();
				QuizzModel quizzNew = new QuizzModel();
				quizzNew.setName(nameQuizz);
				quizzNew.setDescription(descriptionQuizz);
				quizzNew.setImage(fileName);
				quizzService.addQuizz(quizzNew);
				resp.sendRedirect(req.getContextPath() + "/quan-tri/quizz");
			}
			if(req.getParameter("edit") != null) {
				Part filePart = req.getPart("image");
				String fileName = getFileName(filePart);
				if (!fileName.isEmpty()) {
					for (Part part : req.getParts()) {
						part.write(getFolderUpload() + "//" + fileName);
					}
				}
				QuizzModel quizzNew = new QuizzModel();
				int idQuizz = Integer.parseInt(req.getParameter("id").toString());
				String nameQuizz = req.getParameter("name").toString();
				String descriptionQuizz = req.getParameter("description").toString();
				quizzNew.setId(idQuizz);
				quizzNew.setName(nameQuizz);
				quizzNew.setDescription(descriptionQuizz);
				quizzNew.setImage(fileName);
				System.out.println(fileName);
				quizzService.editQuizz(quizzNew);
				resp.sendRedirect(req.getContextPath() + "/quan-tri/quizz");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	public static String getFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public static File getFolderUpload() {
		File folderUpload = new File(
				"D:\\workspace\\Quizz\\JSP_Servlet_JDBC\\Quizz\\src\\main\\webapp\\views\\admin\\images");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
