package kr.teamproject2.app.service;







import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.teamproject2.app.dao.QuizDAO;

public class AdminServiceImp implements AdminService{

	private QuizDAO quizDao;
	public AdminServiceImp() {
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
	public boolean insertQuizType(String qt_name) {
		try {
			return quizDao.insertQuizType(qt_name);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean deleteQuizType(int qt_num) {
		
		return quizDao.deleteQuizType(qt_num);

	}

	@Override
	public boolean updateQuizType(int qt_num, String qt_name) {
		
		return quizDao.updateQuizType(qt_num, qt_name);
	}
	
}




