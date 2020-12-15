package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import model.Question;
import service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService qs;
	
	@PostMapping("/submitAnswer")
	public void submitAnswer(@RequestParam("questionID") int questionID, @RequestParam("answer") String answer, 
			@RequestParam("userid") String userid) {
		qs.submitAnswer(userid, questionID, answer);
	}
	
	@RequestMapping(value="/addAnswers", method=RequestMethod.POST)
	public ModelAndView addAnswers(@RequestParam("questionID") int questionID, @RequestParam("answers[]") String[] answers, @RequestParam("correct") String correct ) {
		qs.addAnswers(questionID, answers, correct);
		return new ModelAndView("admin");
	}
	
	@RequestMapping(value="/deleteQuestion", method=RequestMethod.DELETE)
	public void deleteQuestion(@RequestParam("questionID") int questionID) {
		qs.deleteQuestion(questionID);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public List<Question> getAll() {
		return qs.getAll();
	}
	
	@RequestMapping(value="/percentagesOfTrueQuestions", method=RequestMethod.GET)
	public List<Question> getPercOfTrueQuestions() {
		return qs.getPercOfTrueQuestions();
	}
	
	@RequestMapping(value="/percentagesOfFalseQuestions", method=RequestMethod.GET)
	public List<Question> getPercOfFalseQuestions() {
		return qs.getPercOfFalseQuestions();
	}
	
	@RequestMapping(value="/addQuestion", method=RequestMethod.POST) 
	public void addQuestion(@RequestParam("content") String content) {
		qs.addQuestion(new Question(content));
	}
	
	@RequestMapping(value="/getQuestionAnswers", method=RequestMethod.GET) 
	public List<Question> getQuestionAnswers(@RequestParam("questionid") String questionid) {
		System.out.println(questionid); 
		return qs.getQuestionAnswers(questionid);
	}
}
