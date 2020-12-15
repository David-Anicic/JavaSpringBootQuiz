package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.QuestionAnswersJoin;

@SuppressWarnings("rawtypes")
public class QuestionAnswersJoinResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		QuestionAnswersJoin qa = new QuestionAnswersJoin();
		qa.setQuestionID(rs.getInt(1));
		qa.setQuestionContent(rs.getString(2));
		qa.setAnswerContent(rs.getString(3));
		return qa;
	}
}
