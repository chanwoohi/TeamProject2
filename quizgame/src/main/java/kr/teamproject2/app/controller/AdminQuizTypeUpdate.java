
package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.service.AdminService;
import kr.teamproject2.app.service.AdminServiceImp;


@WebServlet("/admin/quiz/update")
public class AdminQuizTypeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminServiceImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("qt_num");
		
		String qt_name = request.getParameter("qt_name");
		try {
			int qt_num = Integer.parseInt(num);
			
			if(adminService.updateQuizType(qt_num, qt_name)) {
				request.setAttribute("msg", "퀴즈종료 수정에 성공했습니다.");
			}else {
				throw new RuntimeException();
			}
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "퀴즈종류 수정에 실패했습니다.");
		}
		request.setAttribute("url", "/admin/quiz");
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}