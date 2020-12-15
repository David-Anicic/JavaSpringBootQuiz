package model;

public class Question {

	private int QuestionID;
	private String Content;
	private String contentAnswer;
	private int count;
	private double percentage;

	public Question() {
	}

	public Question(String content) {
		this.Content = content;
	}

	public String getContentAnswer() {
		return contentAnswer;
	}

	public void setContentAnswer(String contentAnswer) {
		this.contentAnswer = contentAnswer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getQuestionID() {
		return QuestionID;
	}

	public void setQuestionID(int questionID) {
		QuestionID = questionID;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
