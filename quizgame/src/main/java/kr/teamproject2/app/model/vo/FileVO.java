package kr.teamproject2.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileVO {
	private int fi_num;
	private String fi_ori_name; 
	private String fi_name;
	private int fi_po_num;
}
