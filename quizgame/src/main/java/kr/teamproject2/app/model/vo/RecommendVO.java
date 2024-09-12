package kr.teamproject2.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendVO {
	private int re_num;
	private int re_state;
	private String re_me_id; 
	private int re_po_num;
	
	public RecommendVO(int po_num, int re_state, String me_id) {
		this.re_po_num = po_num;
		this.re_state = re_state;
		this.re_me_id = me_id;
	}
}