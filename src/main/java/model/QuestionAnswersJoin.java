package model;

public class QuestionAnswersJoin {

	private int QuestionID;
	private int AnswerID;
	private String AnswerContent;
	private String QuestionContent;

	public QuestionAnswersJoin() {
	}

	public int getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(int questionID) {
		QuestionID = questionID;
	}

	public int getAnswerID() {
		return AnswerID;
	}

	public void setAnswerID(int answerID) {
		AnswerID = answerID;
	}

	public String getAnswerContent() {
		return AnswerContent;
	}

	public void setAnswerContent(String content) {
		AnswerContent = content;
	}
	
	public String getQuestionContent() {
		return QuestionContent;
	}
	
	public void setQuestionContent(String content) {
		this.QuestionContent = content;
	}
}
