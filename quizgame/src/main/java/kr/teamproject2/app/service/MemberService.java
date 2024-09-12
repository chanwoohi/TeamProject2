package kr.teamproject2.app.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import kr.teamproject2.app.model.vo.MemberVO;

public interface MemberService {

	boolean signup(MemberVO member);

	MemberVO login(MemberVO member);

	Cookie createCookie(MemberVO user, HttpServletRequest request);

	void updateMemberCookie(MemberVO user);
	
	MemberVO getMemberBySid(String sid);
	
	boolean checkId(String me_id);

	List<MemberVO> selectMemberList();
	
	MemberVO pointUp(MemberVO user);

	MemberVO pointDown(MemberVO user);

	MemberVO selectMember(String me_id);

	boolean updateMember(MemberVO member);

}
