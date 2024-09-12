package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.service.QuizService;
import kr.teamproject2.app.service.QuizServiceImp;


@WebServlet("/quiz2/quizinsert")
public class QuizInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuizService quizService = new QuizServiceImp();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qtNumStr = request.getParameter("qt_num");
		
		try {
			int qt_num = Integer.parseInt(qtNumStr);
			request.setAttribute("qt_num", qt_num);
			request.getRequestDispatcher("/WEB-INF/views/quiz2/quizinsert.jsp").forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg","잘못된 퀴즈입니다.");
			request.setAttribute("url", "/quiz2/quizlist");
			request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qtNumStr = request.getParameter("qt_num");
		
		try {
			int qu_qt_num = Integer.parseInt(qtNumStr);
			String content = request.getParameter("content");
			String answer1 = request.getParameter("answer1");
			String answer2 = request.getParameter("answer2");
			String answer3 = request.getParameter("answer3");
			String answer4 = request.getParameter("answer4");
			int correct_answer = Integer.parseInt(request.getParameter("answer"));
			
			QuizVO quiz = new QuizVO(content,answer1,
					answer2,answer3,answer4,correct_answer, qu_qt_num);
			if(quizService.insertQuiz(quiz)) {
				request.setAttribute("msg", "퀴즈 등록완료");
				request.setAttribute("url", "/quiz2/quizlist?qt_num="+qu_qt_num);
			}
			else {
				throw new RuntimeException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "퀴즈 등록 실패");
			request.setAttribute("url", "/quiz2/quizlist?qt_num="+qtNumStr);
			
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}