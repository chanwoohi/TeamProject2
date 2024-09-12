package kr.teamproject2.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.teamproject2.app.model.vo.CommentVO;
import kr.teamproject2.app.model.vo.CommunityVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.model.vo.QuizAttemptVO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.model.vo.RecommendVO;
import kr.teamproject2.app.pagination.Criteria;

public interface PostDAO {

	List<CommunityVO> selectCommunityList();

	CommunityVO selectCommunity(@Param("co_num")String num);

	List<PostVO> selectPostList(@Param("cri")Criteria cri);

	int selectPostTotalCount(@Param("cri")Criteria cri);

	PostVO selectPost(@Param("po_num")int num);

	void updatePostView(@Param("po_num")String po_num);

	boolean insertPost(@Param("post")PostVO post);

	boolean updatePost(@Param("post")PostVO post);

	void deletePost(@Param("po_num")String po_num);

	CommentVO selectComment(@Param("cm_num")int cm_num);

	boolean deleteComment(@Param("cm_num")int cm_num);

	List<CommentVO> selectCommentList(@Param("cri")Criteria cri);

	int selectCommentTotalCount(@Param("cri")Criteria cri);
	
	boolean insertComment(@Param("cm")CommentVO comment);
	
	boolean updateComment(@Param("cm")CommentVO comment);

	List<QuizVO> selectQuizTypeList();

	QuizTypeVO selectQuizType(@Param("qt_num")int qt_num);

	List<QuizVO> selectQuizList(@Param("qt_num")int qt_num);

	QuizVO selectQuiz(@Param("qu_num")int qu_num);

	void insertAttempt(@Param("qa")QuizAttemptVO attempt);

	List<PostVO> selectPostListRanking();
	
	RecommendVO selectRecommend(@Param("re")RecommendVO recommend);

	void insertRecommend(@Param("re")RecommendVO recommend);

	void deleteRecommend(@Param("re_num")int re_num);

}
