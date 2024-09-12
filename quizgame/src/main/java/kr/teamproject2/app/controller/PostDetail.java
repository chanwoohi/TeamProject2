package kr.teamproject2.app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.model.vo.RecommendVO;
import kr.teamproject2.app.service.PostService;
import kr.teamproject2.app.service.PostServiceImp;

@WebServlet("/post/detail")
public class PostDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//게시글 정보를 가져와서 화면에 전달
		//1. 게시글 번호를 가져옴
		String po_num = request.getParameter("po_num");
		try {
			//게시글 조회수를 증가
			postService.updatePostView(po_num);
			int num = Integer.parseInt(po_num);
			//2. 게시글 번호를 이용해서 게시글 정보를 가져옴
			PostVO post = postService.getPost(num);
			//3. 게시글 정보를 화면에 전송
			request.setAttribute("post", post);
			// 로그인한 회원의 추천 정보를 가져옴 
			MemberVO user = (MemberVO) request.getSession().getAttribute("user");
			RecommendVO recommend = postService.getRecommend(num, user);
			request.setAttribute("re", recommend);

			/* 비밀글 여부 확인 및 권한 체크
           		if (post.getPo_secret().equals("Y")) {
               	   if (user == null || !user.getMe_id().equals(post.getPo_me_id())) {
                   // 비밀글이면서 작성자 또는 관리자가 아니면 접근 제한
                   request.setAttribute("msg", "비밀글은 작성자 또는 관리자만 볼 수 있습니다.");
                   request.setAttribute("url", "/post/list?");
                   request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
                   return;
               }
           }*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/views/post/detail.jsp").forward(request, response);
	}

}

