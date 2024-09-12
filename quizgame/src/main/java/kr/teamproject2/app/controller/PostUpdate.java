package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.service.PostService;
import kr.teamproject2.app.service.PostServiceImp;

@WebServlet("/post/update")
public class PostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String po_numStr = request.getParameter("po_num");
		int po_num = Integer.parseInt(po_numStr);
		PostVO post = postService.getPost(po_num);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/post/update.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//제목, 게시글 번호, 내용을 가져옴
		String po_num = request.getParameter("po_num");
		String title = request.getParameter("po_title");
		String content = request.getParameter("po_content");
		String secret = request.getParameter("po_secret");
		//로그인한 회원 정보를 가져옴
		MemberVO user = (MemberVO)request.getSession().getAttribute("user");
		//제목, 게시글 번호, 내용을 이용해서 객체를 생성
		PostVO post = new PostVO(po_num, title, content);
		//객체를 이용해서 수정하라고 요청
		boolean res = postService.updatePost(post, user);
		//수정 여부에 따라 알림
		if(res) {
			request.setAttribute("msg", "게시글 수정했습니다.");
			request.setAttribute("url", "/post/detail?po_num="+po_num);
		}
		else {
			request.setAttribute("msg", "게시글 수정에 실패했습니다.");
			request.setAttribute("url", "/post/update?po_num="+po_num);
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}

}
