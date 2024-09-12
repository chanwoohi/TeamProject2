package kr.teamproject2.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.teamproject2.app.model.vo.CommentVO;
import kr.teamproject2.app.pagination.CommentCriteria;
import kr.teamproject2.app.pagination.Criteria;
import kr.teamproject2.app.pagination.PageMaker;
import kr.teamproject2.app.service.PostService;
import kr.teamproject2.app.service.PostServiceImp;

@WebServlet("/comment/list")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService postService = new PostServiceImp();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("num");
		String pageStr = request.getParameter("page");

		JSONObject jobj = new JSONObject();
		ObjectMapper om = new ObjectMapper();
		try {
			int po_num = Integer.parseInt(num);
			int page = Integer.parseInt(pageStr);
			Criteria cri = new CommentCriteria(page, 2, po_num); 
			//댓글 목록
			List<CommentVO> list = postService.getCommentList(cri);
			//댓글 페이지네이션
			PageMaker pm = postService.getCommentPageMaker(cri);
			jobj.put("list", list);
			jobj.put("pm", om.writeValueAsString(pm));
		}catch(NumberFormatException e) {
			
		}catch(Exception e) {
			
		}
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jobj);
	}

}