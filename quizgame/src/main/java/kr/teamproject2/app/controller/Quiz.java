package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;
import kr.teamproject2.app.model.vo.QuizAttemptVO;
import kr.teamproject2.app.service.PostService;
import kr.teamproject2.app.service.PostServiceImp;

@WebServlet("/post/quiz")
public class Quiz extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	private MemberService memberService = new MemberServiceImp();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    String qt_numStr = request.getParameter("qt_num");
	    try {
	        int qt_num = Integer.parseInt(qt_numStr);
	        System.out.println(qt_num);
	        QuizTypeVO quizType = postService.getQuiz(qt_num);
	        if (quizType == null) {
	            throw new RuntimeException();
	        }
	        System.out.println(qt_num);
	        List<QuizVO> allList = postService.getQuizList(qt_num);
	        Collections.shuffle(allList);
	        List<QuizVO> list = allList.subList(0, 2);
	        session.setAttribute("list", list);
	        session.setAttribute("index", 0);
	        if (!list.isEmpty()) {
	            QuizVO quiz = list.get(0);
	            request.setAttribute("quiz", quiz);
	        }
	        session.setAttribute("quizType", quizType);
	        request.setAttribute("qt", quizType);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return;
	    }
	    request.getRequestDispatcher("/WEB-INF/views/post/quiz.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<QuizVO> list = (List<QuizVO>)session.getAttribute("list");
	    Integer index = (Integer) session.getAttribute("index");
	    Integer count = (Integer) session.getAttribute("count");
	    Integer score = (Integer) session.getAttribute("score");
	    if(count == null && score == null) {
	    	count = 0;
	    	score = 0;
	    }
	    if (list == null || index == null) {
	        return;
	    }
	    String user_answerStr = request.getParameter("answer");
	    QuizVO correct = list.get(index);
	    try {
	        int user_answer = Integer.parseInt(user_answerStr);
	        int correct_answer = correct.getQu_correct_answer();
	        MemberVO user = (MemberVO)session.getAttribute("user");
	        if(user == null) {
	        	throw new RuntimeException();
	        }
	        if(user_answer == correct_answer) {
	        	user = memberService.pointUp(user);
	        	count++;
	        	score += 20;
	        }
	        else {
	        	user = memberService.pointDown(user);
	        	score -= 20;
	        }
	        session.setAttribute("count", count);
	        session.setAttribute("score", score);
	        index++;
	        if (index < list.size()) {
	            QuizVO quiz = list.get(index);
	            session.setAttribute("index", index);
	            request.setAttribute("quiz", quiz);
	        } else {
	        	request.setAttribute("user", user);
	        	request.setAttribute("count", count);
	        	request.setAttribute("score", score);
	            session.removeAttribute("list");
	            session.removeAttribute("index");
	            session.removeAttribute("count");
	            session.removeAttribute("score");
	        }
	        request.setAttribute("qt", session.getAttribute("quizType"));
	    } catch (Exception e) {
	        e.printStackTrace();
	        return;
	    }
	    request.getRequestDispatcher("/WEB-INF/views/post/quiz.jsp").forward(request, response);
	}

}
