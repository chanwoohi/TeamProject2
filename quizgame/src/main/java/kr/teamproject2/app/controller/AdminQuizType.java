package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.service.QuizService;
import kr.teamproject2.app.service.QuizServiceImp;


@WebServlet("/admin/quiz")
public class AdminQuizType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizService quizService = new QuizServiceImp();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuizTypeVO> list = quizService.getQuizType();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/admin/quiz.jsp").forward(request, response);
	}

	
	
}

