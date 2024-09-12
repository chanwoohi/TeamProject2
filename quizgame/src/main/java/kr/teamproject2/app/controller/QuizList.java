package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.service.QuizService;
import kr.teamproject2.app.service.QuizServiceImp;


@WebServlet("/quiz2/quizlist")
public class QuizList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuizService quizService = new QuizServiceImp();
   
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qtNum = 0;
		
		try {
			qtNum = Integer.parseInt(request.getParameter("qt_num"));
			
			QuizTypeVO quiz = quizService.getQuizType(qtNum);
			if(quiz == null) {
				throw new Exception();
			}
			List<QuizVO> list= quizService.getQuizList(qtNum);
			
			request.setAttribute("qt", quiz);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/quiz2/quizlist.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "잘못된 퀴즈목록입니다.");
			request.setAttribute("url", "/admin/quiz");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}

	
	

}