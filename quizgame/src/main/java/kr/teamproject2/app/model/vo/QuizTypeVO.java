package kr.teamproject2.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizTypeVO {

	private int qt_num;
	private String qt_name;
	
	public QuizTypeVO(String qt_name) {
		this.qt_name = qt_name;
	}
}
