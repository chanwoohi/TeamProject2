package kr.teamproject2.app.service;

import java.util.List;

import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;

public interface QuizService {

	List<QuizTypeVO> getQuizType();

	QuizTypeVO getQuizType(int qtNum);

	List<QuizVO> getQuizList(int qtNum);

	boolean insertQuiz(QuizVO quiz);

	QuizVO getQuiz(int num);

	boolean deleteQuiz(String qu_num);

	boolean deleteAll(String[] qu_nums);

}