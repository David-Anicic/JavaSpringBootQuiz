package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.DBASinglton;

import dao.mapper.MyRowMapper;
import model.Question;

@Service
public class QuestionService {

	private JdbcTemplate jdbc;
	
	public QuestionService() {
		jdbc = com.example.demo.DBASinglton.getDBASingltonInstance().getJdbcTemplate();
	}
	
	public void addQuestion(Question q) {
		jdbc.update("insert into Question(content) values(?)", q.getContent());
	}

	@SuppressWarnings("unchecked")
	public List<Question> getAll() {
		return jdbc.query("select * from question", new MyRowMapper("ClassicQuestion"));
	}

	@SuppressWarnings("unchecked")
	public List<Question> getPercOfTrueQuestions() {

		// ukupan broj odgovara na pitanje
		List<Question> l1 = jdbc.query("select q.content, count(qu.questionid) totalCount\r\n" + 
				"from questionsusers qu join question q on qu.questionid=q.questionid\r\n" + 
				"group by qu.questionid, q.content", new MyRowMapper("Question"));

		// ukupan broj tacnih odgovara na pitanje
		List<Question> l2 = jdbc.query("select q.content, count(qu.questionid)\r\n" + 
				"from questionanswer qa join questionsusers qu on qa.questionid=qu.questionid\r\n" + 
				"	and qa.content=qu.answer and qa.correct=1 join question q on q.questionid=qa.questionid\r\n" + 
				"group by qu.questionid, q.content", new MyRowMapper("Question"));
		
		int i=0;
		for (Question q : l1) {
			if(i+1 > l2.size())
				q.setPercentage(0.0);
			else
				q.setPercentage((double)l2.get(i).getCount()/(double)q.getCount());
			q.setPercentage(q.getPercentage()*100);
			
			i++;
		}
		
		for (i = 0; i < l1.size()-1; i++)
			for(int j=i+1;j<l1.size(); j++) {
				Question q1 = l1.get(i);
				Question q2 = l1.get(j);
				
				if(q1.getPercentage() < q2.getPercentage()) {
					Question pom = q1;
					l1.set(i, q2);
					l1.set(j, pom);
				}
			}
		
		
		return l1;
	}

	public List<Question> getPercOfFalseQuestions() {
		
		@SuppressWarnings("unchecked")
		// za svako pitanje i odgovor broj netacnih odgovoranja
		List<Question> l1 = jdbc.query("select q.questionid, q.content contentQuestion, qa.content contentAnswer, count(*) count\r\n" + 
				"from questionanswer qa join questionsusers qu on\r\n" + 
				"	qa.questionid=qu.questionid and qa.content=qu.answer and\r\n" + 
				"	qa.correct=0 join question q on qa.questionid=q.questionid\r\n" + 
				"group by qa.questionid, q.content, qa.answerid, qa.content\r\n" + 
				"order by qa.questionid, count desc", new MyRowMapper("QuestionFalsePerc"));
		
		// za svako pitanje broj netacnih odgovoranja
		@SuppressWarnings("unchecked")
		List<Question> l2 = jdbc.query("select qa.questionid, qa.content, qu.answer, count(*) totalCount\r\n" + 
				"from questionanswer qa join questionsusers qu on qa.questionid=qu.questionid\r\n" + 
				"	and qa.correct=0 and qa.content=qu.answer\r\n" + 
				"group by qa.questionid", new MyRowMapper("QuestionFalsePerc"));
		
		if(l1.size() > 0) {
		
			List<Question> finalList = new ArrayList<Question>();
			
			int j=0;
			int currentQuestionID = l1.get(0).getQuestionID();
			Question q = l1.get(0);
			q.setPercentage((double)q.getCount()/(double)l2.get(j).getCount());
			q.setPercentage(q.getPercentage()*100);
			
			j++;
			finalList.add(q);
			
			for (int i = 1; i < l1.size(); i++) {
				while(l1.get(i).getQuestionID()==currentQuestionID)
					continue;
				currentQuestionID = l1.get(i).getQuestionID();
				
				q = l1.get(i);
				q.setPercentage((double)q.getCount()/(double)l2.get(j).getCount());
				q.setPercentage(q.getPercentage()*100);
				finalList.add(q);
			}
			return finalList;
		}
		
		return null;
	}

	public void deleteQuestion(int questionID) {
		jdbc.update("DELETE FROM question WHERE questionID=?", new Object[] { questionID });
	}

	public void addAnswers(int questionID, String[] answers, String correct) {
		String sql = "INSERT INTO questionanswer(questionid, correct, content) VALUES (?, ?, ?)";
		Connection connection = null;
		PreparedStatement ps;
		int pom, numCorrect = Integer.parseInt(correct);
		
		try {
			connection = DBASinglton.getDBASingltonInstance().getDataSource().getConnection();
			ps = connection.prepareStatement(sql);
			
			for (int i = 0; i < answers.length; i++) {
				ps.setInt(1, questionID);
				if(numCorrect==i)
					pom = 1;
				else
					pom = 0;
				ps.setInt(2, pom);
				ps.setString(3, answers[i]);
				
				ps.addBatch();
			}
			
			ps.executeBatch();
			// ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void submitAnswer(String userid, int questionid, String answer) {
		String sql = "INSERT INTO questionsusers(userid, questionid, answer) VALUES (?, ?, ?)";
		
		Connection connection = null;
		PreparedStatement ps;
		try {
			connection = DBASinglton.getDBASingltonInstance().getDataSource().getConnection();
			ps = connection.prepareStatement(sql);

			ps.setInt(1, Integer.parseInt(userid));
			ps.setInt(2, questionid);
			ps.setString(3, answer);
			
			ps.addBatch();
			ps.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Question> getQuestionAnswers(String questionid) {
		int qid = Integer.parseInt(questionid);
		return jdbc.query("select q.questionid, q.content qcontent, qa.content acontent\r\n" + 
				"from question q join questionanswer qa on q.questionid=qa.questionid\r\n" + 
				"where q.questionid=?", new Object[] { qid }, new MyRowMapper("QuestionAnswersJoin"));
	}
}
