package kr.teamproject2.app.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.teamproject2.app.dao.PostDAO;
import kr.teamproject2.app.model.vo.CommentVO;
import kr.teamproject2.app.model.vo.CommunityVO;
import kr.teamproject2.app.model.vo.MemberVO;
import kr.teamproject2.app.model.vo.PostVO;
import kr.teamproject2.app.pagination.Criteria;
import kr.teamproject2.app.pagination.PageMaker;
import kr.teamproject2.app.model.vo.QuizAttemptVO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;
import kr.teamproject2.app.model.vo.RecommendVO;

public class PostServiceImp implements PostService {

	private PostDAO postDao;
	
	public PostServiceImp() {
		String resource = "kr/teamproject2/app/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {

			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			postDao = session.getMapper(PostDAO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CommunityVO> getCommunityList() {
		return postDao.selectCommunityList();
	}

	@Override
	public CommunityVO getCommunity(String num) {
		return postDao.selectCommunity(num);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectPostList(cri);
	}

	@Override
	public PageMaker getPostPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.selectPostTotalCount(cri);
		return new PageMaker(totalCount, 5, cri);
	}

	@Override
	public PostVO getPost(int num) {
		return postDao.selectPost(num);
	}

	@Override
	public void updatePostView(String po_num) {
		postDao.updatePostView(po_num);
	}

	@Override
	public boolean insertPost(PostVO post) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0 ) {
			return false;
		}
		try {
			return postDao.insertPost(post);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public boolean updatePost(PostVO post, MemberVO user) {
		if(post == null) {
			return false;
		}
		if(post.getPo_title() == null || post.getPo_title().trim().length() == 0 ) {
			return false;
		}
		PostVO dbPost = postDao.selectPost(post.getPo_num());
		if( dbPost == null ||
			user == null ||
			!dbPost.getPo_me_id().equals(user.getMe_id())) {
			return false;
		}
		try {
			return postDao.updatePost(post);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int deletePost(String po_num, MemberVO user) {
		if(user == null) {
			return 0;
		}
		PostVO post = postDao.selectPost(Integer.parseInt(po_num));
		if(post == null) {
			return 0;
		}
		if(!post.getPo_me_id().equals(user.getMe_id())) {
			return 0;
		}
		postDao.deletePost(po_num);
		return post.getPo_co_num();
	}
	
	@Override
	public List<CommentVO> getCommentList(Criteria cri) {
		if(cri == null) {
			return null;
		}
		return postDao.selectCommentList(cri);
	}

	@Override
	public PageMaker getCommentPageMaker(Criteria cri) {
		if(cri == null) {
			return null;
		}
		int totalCount = postDao.selectCommentTotalCount(cri);
		return new PageMaker(totalCount, 2, cri);
	}
	
	@Override
	public boolean insertComment(CommentVO comment) {
		if(comment == null) {
			return false;
		}
		if(comment.getCm_content()== null || comment.getCm_content().trim().length() == 0) {
			return false;
		}
		return postDao.insertComment(comment);
	}


	@Override
	public boolean deleteComment(int co_num, MemberVO user) {
		if(user == null) {
			return false;
		}
		//작성자가 맞는지 확인
		CommentVO comment = postDao.selectComment(co_num);
		if(comment == null) {
			return false;
		}
		if(!comment.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		//맞으면 삭제 요청
		return postDao.deleteComment(co_num);
	}
	
	@Override
	public boolean updateComment(CommentVO comment, MemberVO user) {
		if(user == null || comment == null) {
			return false;
		}
		//작성자가 맞는지 확인
		CommentVO dbComment = postDao.selectComment(comment.getCm_num());
		if(dbComment == null) {
			return false;
		}
		if(!dbComment.getCm_me_id().equals(user.getMe_id())) {
			return false;
		}
		//맞으면 삭제 요청
		return postDao.updateComment(comment);
	}


	public List<QuizVO> getQuizTypeList() {
		return postDao.selectQuizTypeList();
	}

	@Override
	public QuizTypeVO getQuiz(int qt_num) {
		return postDao.selectQuizType(qt_num);
	}

	@Override
	public List<QuizVO> getQuizList(int qt_num) {
		return postDao.selectQuizList(qt_num);
	}

	@Override
	public QuizVO getQuizById(int qu_num) {
		return postDao.selectQuiz(qu_num);
	}

	@Override
	public List<PostVO> getPostListRanking() {
		return postDao.selectPostListRanking();
	}

	@Override
	public RecommendVO getRecommend(int num, MemberVO user) {
		if(user == null) {
			return null;
		}//추천버튼 누르면 로그인창으로
		RecommendVO recommend = new RecommendVO(num, 0, user.getMe_id());
		return postDao.selectRecommend(recommend);
	}

	@Override
	public int insertRecommend(RecommendVO recommend) {
		if(recommend == null) {
			throw new RuntimeException();
		}
		//기존에 추천 내용을 확인
		RecommendVO dbRecommend = postDao.selectRecommend(recommend);
		
		//없으면 추가 후 추천상태를 리턴
		if(dbRecommend == null){
			postDao.insertRecommend(recommend);
			return recommend.getRe_state();
		}
		//있으면 삭제 
		postDao.deleteRecommend(dbRecommend.getRe_num());
		
		//기존 상태와 새 상태가 같으면(취소)
		if(dbRecommend.getRe_state() == recommend.getRe_state()) {
			return 0;
		}
		//기존상태와 새 상태가 다르면(변경)
		postDao.insertRecommend(recommend);
		return recommend.getRe_state();
	}


}
