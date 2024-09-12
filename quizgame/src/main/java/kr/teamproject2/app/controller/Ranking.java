package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;

@WebServlet("/ranking")
public class Ranking extends HttpServlet {
	
	private MemberService memberService = new MemberServiceImp();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MemberVO> list = memberService.selectMemberList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/member/ranking.jsp").forward(request, response);
	}


}
