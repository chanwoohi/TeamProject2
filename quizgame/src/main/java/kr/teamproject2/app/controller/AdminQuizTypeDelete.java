
package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.service.AdminService;
import kr.teamproject2.app.service.AdminServiceImp;


@WebServlet("/admin/quiz/delete")
public class AdminQuizTypeDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("qt_num");
		try {
			int qt_num = Integer.parseInt(num);
			
			if(adminService.deleteQuizType(qt_num)) {
				request.setAttribute("msg", "퀴즈종류 삭제에 성공하셨습니다.");
			}else {
				throw new RuntimeException();
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("msg", "퀴즈종류 삭제에 실패하셨습니다.");
		}
		request.setAttribute("url", "/admin/quiz");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}


}