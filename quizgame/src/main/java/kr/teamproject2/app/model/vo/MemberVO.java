package kr.teamproject2.app.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {
	private String me_id;
	private String me_pw; 
	private String me_email; 
	private String me_authority; 
	private int me_point; 
	private String me_cookie;
	private Date me_limit; 
	private String me_ms_name;
	private int me_ranking;
	private String me_address;
	private String me_addressDetail;
	private String me_phoneNumberMid;
	private String me_phoneNumberEnd;
	
	
	public MemberVO(String me_id, String me_pw, String me_email, String me_address, String me_addressDetail,
					String me_phoneNumberMid, String me_phoneNumberEnd ) {
		this.me_id = me_id;
		this.me_pw = me_pw;
		this.me_email = me_email;
		this.me_address = me_address;
		this.me_addressDetail = me_addressDetail;
		this.me_phoneNumberMid = me_phoneNumberMid;
		this.me_phoneNumberEnd = me_phoneNumberEnd;

	}

}
