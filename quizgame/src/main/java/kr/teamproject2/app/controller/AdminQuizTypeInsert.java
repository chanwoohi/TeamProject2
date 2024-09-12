
package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.service.AdminService;
import kr.teamproject2.app.service.AdminServiceImp;


@WebServlet("/admin/quiz/insert")

public class AdminQuizTypeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qt_name = request.getParameter("qt_name");
		
		if(adminService.insertQuizType(qt_name)) {
			request.setAttribute("url", "/admin/quiz");
			request.setAttribute("msg", "퀴즈종류 등록 성공");
		}else {
			request.setAttribute("url", "/admin/quiz");
			request.setAttribute("msg", "퀴즈종류 등록 실패");
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}