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

@WebServlet("/post/insert")
public class PostInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PostService postService = new PostServiceImp();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//커뮤니티 번호를 가져와서 화면에 전달
		String co_num = request.getParameter("co_num");
		request.setAttribute("co_num", co_num);
		
		request.getRequestDispatcher("/WEB-INF/views/post/insert.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		//화면에서 게시글 정보를 가져와서 게시글을 등록
		//1. 제목, 내용, 커뮤니티 번호를 가져옴
		String coNumStr = request.getParameter("po_co_num");
		try {
			int co_num = Integer.parseInt(coNumStr);
			String title = request.getParameter("po_title");
			String content = request.getParameter("po_content");
			//비밀글을 가져옴
			String poSecret = request.getParameter("po_secret"); //비밀글
			//비밀글 체크박스 값처리
			if(poSecret == null) {
				 poSecret = "N"; // 체크되지 않으면 비밀글 아님(체크된 경우 true, 그렇지 않으면 false)
			}else {
				poSecret ="Y";
			}
	        String coNum = request.getParameter("po_co_num");
			
			//2. 로그인한 회원 정보를 가져옴
			MemberVO user = (MemberVO)request.getSession().getAttribute("user");
			if(user == null) {
				throw new RuntimeException();
			}
			
	        // 비밀글일 경우 알림 메시지 설정
	        if (poSecret.equals("Y")) {
	            request.setAttribute("message", "비밀글로 등록되었습니다.");
	        } else {
	            request.setAttribute("message", "게시글이 정상적으로 등록되었습니다.");
	        }
			String id = user.getMe_id();
			
			//3. 제목, 내용, 커뮤니티, 회원 아이디를 이용해서 게시글 객체를 생성
			PostVO post = new PostVO(co_num, title, content, id, poSecret);
			
			//4. 게시글 객체를 등록 시킴
			if(postService.insertPost(post)) {
				//비밀글 여부에 따라 알림 메세지 설정
                if (poSecret.equals("Y")) {
                    request.setAttribute("msg", "비밀글로 등록되었습니다.");
                } else {
                    request.setAttribute("msg", "게시글이 정상적으로 등록되었습니다.");
                }
                request.setAttribute("url", "/post/list?co_num=" + co_num);
            } else {
                throw new RuntimeException();
            }
		}catch(Exception e) {
			e.printStackTrace();
			//게시글 등록에 실패했다고 알림
			request.setAttribute("msg", "게시글 등록에 실패했습니다.");
			request.setAttribute("url", "/post/list?co_num="+coNumStr);
		}
		request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
	}
}
