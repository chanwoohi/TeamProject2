package kr.teamproject2.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizVO {
	
	private int qu_num; 
	private String qu_content; 
	private String qu_answer1; 
	private String qu_answer2; 
	private String qu_answer3; 
	private String qu_answer4; 
	private int qu_correct_answer;
	private int qu_qt_num;

	public QuizVO(String content, String answer1, String answer2, String answer3, String answer4, int correct_answer, int qu_qt_num) {
		this.qu_content=content;
		this.qu_answer1=answer1;
		this.qu_answer2=answer2;
		this.qu_answer3=answer3;
		this.qu_answer4=answer4;
		this.qu_correct_answer=correct_answer;
		this.qu_qt_num = qu_qt_num;
	}
	
}