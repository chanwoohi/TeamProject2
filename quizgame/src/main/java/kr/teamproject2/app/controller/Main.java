package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;
import kr.teamproject2.app.service.PostService;
import kr.teamproject2.app.service.PostServiceImp;

@WebServlet("/")
public class Main extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	private MemberService memberService = new MemberServiceImp();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuizVO> quizList = postService.getQuizTypeList();
		List<MemberVO> memberList = memberService.selectMemberList();
		List<PostVO> postList = postService.getPostListRanking();

		request.setAttribute("quizList", quizList);
		request.setAttribute("memberList", memberList);
		request.setAttribute("postList", postList);
		request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
	}
	
}