package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;

@WebServlet("/login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_id = request.getParameter("me_id");
		String me_pw = request.getParameter("me_pw");
		String me_email = request.getParameter("me_email");
		String me_address = request.getParameter("me_address");
		String me_addressDetail = request.getParameter("me_addressDetail");
		String me_phoneNumberMid = request.getParameter("me_phoneNumberMid");
		String me_phoneNumberEnd = request.getParameter("me_phoneNumberEnd");
		
		//2. 아이디, 비번을 이용해서 회원 정보를 가져옴
		MemberVO member = new MemberVO(me_id, me_pw, me_email, me_address, me_addressDetail, 
				me_phoneNumberMid, me_phoneNumberEnd);
		MemberVO user = memberService.login(member);
		//3. 가져온 회원 정보를 세션에 저장
		request.getSession().setAttribute("user", user);
		
		if(user != null) {
			request.setAttribute("msg", "로그인에 성공했습니다.");
			request.setAttribute("url", "/");
			//자동 로그인을 체크했으면,
			String auto = request.getParameter("auto");
			if(auto != null && auto.equals("true")) {
				//쿠키를 생성하고 DB에 쿠키와 만료시간을 저장
				Cookie cookie = memberService.createCookie(user, request);
				response.addCookie(cookie);
			}
		}else {
			request.setAttribute("msg", "로그인에 실패했습니다.");
			request.setAttribute("url", "/login");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
