package kr.teamproject2.app.service;

import java.util.List;

import kr.teamproject2.app.model.vo.QuizTypeVO;

public interface AdminService {

	boolean insertQuizType(String qt_name);


	boolean deleteQuizType(int qt_num);


	boolean updateQuizType(int qt_num, String qt_name);

}
