package kr.teamproject2.app.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizAttemptVO {
	
	private int qa_num;
	private String qa_me_id; 
	private int qa_qu_num;
	private int qa_submitted_answer; 
	private String qa_correct_yn;
	
	public QuizAttemptVO(String me_id, int qu_num, int answer, String check) {
		this.qa_me_id = me_id;
		this.qa_qu_num = qu_num;
		this.qa_submitted_answer = answer;
		this.qa_correct_yn = check;
	}
}
