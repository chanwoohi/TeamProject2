package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;


@WebServlet("/mypage")
public class Mypage extends HttpServlet {
private static final long serialVersionUID = 1L;
    
	private MemberService memberService = new MemberServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_id = request.getParameter("me_id");
		
		MemberVO member = memberService.selectMember(me_id);
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_id = request.getParameter("me_id");
		String me_pw = request.getParameter("me_pw");
		String me_email = request.getParameter("me_email");
		String me_address = request.getParameter("me_address");
		String me_addressDetail = request.getParameter("me_addressDetail");
		String me_phoneNumberMid = request.getParameter("me_phoneNumberMid");
		String me_phoneNumberEnd = request.getParameter("me_phoneNumberEnd");
		
		MemberVO member = new MemberVO(me_id, me_pw, me_email, me_address, me_addressDetail,
									   me_phoneNumberMid, me_phoneNumberEnd);
		
		if(memberService.updateMember(member)) {
			request.setAttribute("msg", "회원 정보 수정에 성공했습니다.");
			request.setAttribute("url", "/");
		} else {
			request.setAttribute("msg", "회원 정보 수정에 실패했습니다.");
			request.setAttribute("url", "/signup");
		}
		
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
