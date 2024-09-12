package kr.teamproject2.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.teamproject2.app.model.vo.QuizTypeVO;
import kr.teamproject2.app.model.vo.QuizVO;

public interface QuizDAO {

	List<QuizTypeVO> selectQuizType();

	boolean insertQuizType(@Param("qt_name")String qt_name);

	boolean insertQuiz(@Param("quiz")QuizVO quiz);

	boolean deleteQuizType(@Param("qt_num")int qt_num);

	boolean updateQuizType(@Param("qt_num")int qt_num,@Param("qt_name") String qt_name);

	QuizTypeVO selectQuizTypeNum(@Param("qt_num")int qtNum);

	List<QuizVO> selectQuizList(@Param("qt_num")int qtNum);

	boolean deleteQuiz(@Param("qu_num")int quNum);

	QuizVO selectQuiz(int num);
	
	boolean deleteQuizzes(String[] qu_nums);
	
}