package kr.teamproject2.app.pagination;

import kr.teamproject2.app.pagination.Criteria;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentCriteria extends Criteria {
	
	private int po_num;
	
	public CommentCriteria(int page, int perPageNum, int po_num) {
		this.page = page;
		this.perPageNum = perPageNum;
		this.po_num = po_num;
	}

}