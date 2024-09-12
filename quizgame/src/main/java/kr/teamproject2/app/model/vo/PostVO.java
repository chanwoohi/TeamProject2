package kr.teamproject2.app.model.vo;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostVO {
	private int po_num; 
	private String po_title;
	private String po_content;
	private String po_me_id;
	private int po_co_num;
	private Date po_date; 
	private int po_view;
	private int po_report;
	private int po_up;
	private int po_down;
	private String po_secret; //비밀글 여부

	public PostVO(int co_num, String title, String content, String id, String poSecret) {
		this.po_co_num = co_num;
		this.po_title = title;
		this.po_content = content;
		this.po_me_id = id;
		this.po_secret = poSecret;
	}

	public PostVO(String po_num, String title, String content) {
		try {
			this.po_num = Integer.parseInt(po_num);
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.po_title = title;
		this.po_content = content;
	}
	
    public String getPo_secret() {
        return po_secret;
    }

    public void setPo_secret(String po_secret) {
        this.po_secret = po_secret;
    }

}