package kr.teamproject2.app.service;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.teamproject2.app.dao.QuizDAO;
import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;

public class QuizServiceImp implements QuizService {

	private QuizDAO quizDao;
	
	public QuizServiceImp() {
		String resource = "kr/teamproject2/app/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {

			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			quizDao = session.getMapper(QuizDAO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<QuizTypeVO> getQuizType() {
		return quizDao.selectQuizType();
	}

	@Override
	public QuizTypeVO getQuizType(int qtNum) {
		return quizDao.selectQuizTypeNum(qtNum);
	}

	@Override
	public List<QuizVO> getQuizList(int qtNum) {
		return quizDao.selectQuizList(qtNum);
	}

	@Override
	public boolean insertQuiz(QuizVO quiz) {
		if(quiz == null) {
			return false;
		}
		if(quiz.getQu_content() == null || quiz.getQu_content().trim().length()==0) {
			return false;
		}
		if(quiz.getQu_answer1() == null || quiz.getQu_answer1().trim().length()==0) {
			return false;
		}
		if(quiz.getQu_answer2() == null || quiz.getQu_answer2().trim().length()==0) {
			return false;
		}
		if(quiz.getQu_answer3() == null || quiz.getQu_answer3().trim().length()==0) {
			return false;
		}
		if(quiz.getQu_answer4() == null || quiz.getQu_answer4().trim().length()==0) {
			return false;
		}
		 int answer = quiz.getQu_correct_answer();
		if( answer < 1 || answer > 4) {
			return false;
		}
		return quizDao.insertQuiz(quiz);
	}

	@Override
	public QuizVO getQuiz(int num) {
		return quizDao.selectQuiz(num);
	}

	@Override
	public boolean deleteQuiz(String qu_num) {
		try {
			int quNum = Integer.parseInt(qu_num);
			return quizDao.deleteQuiz(quNum);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteAll(String[] qu_nums) {
		 boolean result = true;
	        if (qu_nums != null) {
	            for (String qu_num : qu_nums) {
	                result = result && deleteQuiz(qu_num);
	            }
	        }
	        return result;
	}

	}