package kr.teamproject2.app.service;

import java.util.List;

import kr.teamproject2.app.model.vo.CommentVO;
import kr.teamproject2.app.model.vo.CommunityVO;
import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.model.vo.RecommendVO;
import kr.teamproject2.app.pagination.Criteria;
import kr.teamproject2.app.pagination.PageMaker;

public interface PostService {

	List<CommunityVO> getCommunityList();

	CommunityVO getCommunity(String num);

	List<PostVO> getPostList(Criteria cri);

	PageMaker getPostPageMaker(Criteria cri);

	PostVO getPost(int num);

	void updatePostView(String po_num);

	boolean insertPost(PostVO post);

	boolean updatePost(PostVO post, MemberVO user);

	int deletePost(String po_num, MemberVO user);

	boolean deleteComment(int co_num, MemberVO user);
	
	List<CommentVO> getCommentList(Criteria cri);

	PageMaker getCommentPageMaker(Criteria cri);

	boolean insertComment(CommentVO comment);
	
	boolean updateComment(CommentVO comment, MemberVO user);
	
	List<QuizVO> getQuizTypeList();

	QuizTypeVO getQuiz(int qt_num);

	List<QuizVO> getQuizList(int qt_num);

	QuizVO getQuizById(int qu_num);
	
	List<PostVO> getPostListRanking();
	
	RecommendVO getRecommend(int num, MemberVO user);

	int insertRecommend(RecommendVO recommend);
}
