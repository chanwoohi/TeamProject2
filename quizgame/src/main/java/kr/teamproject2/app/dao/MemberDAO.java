package kr.teamproject2.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.teamproject2.app.model.vo.MemberVO;

public interface MemberDAO {

	boolean insertMember(@Param("me")MemberVO member);

	MemberVO selectMember(@Param("me_id")String me_id);

	void updateMemberCookie(@Param("user")MemberVO user);

	MemberVO selectMemberBySid(@Param("sid")String sid);

	List<MemberVO> selectMemberList();

	void updatePointUp(@Param("me_id")String me_id);

	void updatePointDown(@Param("me_id")String me_id);

	boolean updateMember(@Param("me")MemberVO member);

}
