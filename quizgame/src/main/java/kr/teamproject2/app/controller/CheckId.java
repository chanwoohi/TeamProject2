package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import kr.teamproject2.app.service.MemberService;
import kr.teamproject2.app.service.MemberServiceImp;

@WebServlet("/check/id")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String me_id = request.getParameter("me_id");
		
		JSONObject jobj = new JSONObject();
		
		boolean res = memberService.checkId(me_id);
		
		jobj.put("result", res);
		response.setContentType("applicateion/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}
