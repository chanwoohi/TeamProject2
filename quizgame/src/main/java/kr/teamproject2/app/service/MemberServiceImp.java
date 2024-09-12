package kr.teamproject2.app.service;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.teamproject2.app.dao.MemberDAO;
import kr.teamproject2.app.model.vo.MemberVO;


public class MemberServiceImp implements MemberService{

	private MemberDAO memberDao;

	public MemberServiceImp() {
		String resource = "kr/teamproject2/app/config/mybatis-config.xml";
		InputStream inputStream;
		SqlSession session;
		try {

			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sessionFactory.openSession(true);
			memberDao = session.getMapper(MemberDAO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 유효성 검사 메소드
	private boolean checkRegex(String str, String regex) {
		if(str == null || regex == null) {
			return false;
		}
		return Pattern.matches(regex, str);
	}

	@Override
	public boolean signup(MemberVO member) {
		//member null체크
		if(member == null) {
			return false;
		}
		//아이디 유효성 검사
		if(!checkRegex(member.getMe_id(), "^\\w{6,13}$")) {
			return false;
		}

		//비번 유효성 검사
		if(!checkRegex(member.getMe_pw(), "^[a-zA-Z0-9!@#$]{6,15}$")) {
			return false;
		}

		//이메일 유효성 검사
		if(!checkRegex(member.getMe_email(), "^[A-Za-z0-9_]+@[A-Za-z0-9_]+(\\.[A-Za-z]{2,}){1,}$")) {
			return false;
		}

		try {
			return memberDao.insertMember(member);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public MemberVO login(MemberVO member) {
		if(member==null) {
			return null;
		}
		MemberVO user = memberDao.selectMember(member.getMe_id());
		//가입되지 않은 아이디이면
		if(user == null) {
			return null;
		}
		//비번이 같으면
		if(user.getMe_pw().equals(member.getMe_pw())) {
			return user;
		}
		return null;
	}

	@Override
	public Cookie createCookie(MemberVO user, HttpServletRequest request) {
		if(user == null) {
			return null;
		}
		HttpSession session = request.getSession();
		//쿠키는 이름, 값, 만료시간, path
		String me_cookie = session.getId();
		//쿠키 이름이 AL, 값은 현재 세션 아이디값
		Cookie cookie = new Cookie("AL", me_cookie);
		cookie.setPath("/");
		//세션 만료시간을 7일로 설정
		int time = 60 * 60 * 24 * 7;
		cookie.setMaxAge(time);
		user.setMe_cookie(me_cookie);
		//만료시간은 현재 시간 + 1주일 뒤 // 단위가 밀리초라서 * 1000 해주어야 함! 
		Date date = new Date(System.currentTimeMillis() + time * 1000);
		user.setMe_limit(date);
		memberDao.updateMemberCookie(user);
		return cookie;
	}

	@Override
	public void updateMemberCookie(MemberVO user) {
		memberDao.updateMemberCookie(user);
	}
	@Override
	public MemberVO getMemberBySid(String sid) {
		return memberDao.selectMemberBySid(sid);
	}
	@Override
	public boolean checkId(String me_id) {
		return memberDao.selectMember(me_id) == null;
	}
	@Override
	public List<MemberVO> selectMemberList() {
		return memberDao.selectMemberList();
	}
	
	public MemberVO pointUp(MemberVO user) {
		if(user == null) {
			return null;
		}
		memberDao.updatePointUp(user.getMe_id());
		return memberDao.selectMember(user.getMe_id());
	}

	@Override
	public MemberVO pointDown(MemberVO user) {
		if(user == null) {
			return null;
		}
		memberDao.updatePointDown(user.getMe_id());
		return memberDao.selectMember(user.getMe_id());
	}
	@Override
	public MemberVO selectMember(String me_id) {
		if(me_id == null) {
			return null;
		}
		return memberDao.selectMember(me_id);
	}
	@Override
	public boolean updateMember(MemberVO member) {
		if(member == null) {
			return false;
		}
		return memberDao.updateMember(member);
	}
}
