package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.Question;

@SuppressWarnings("rawtypes")
public class QuestionFalsePerc implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		Question q = new Question();
		
		q.setQuestionID(rs.getInt(1));
		q.setContent(rs.getString(2));
		q.setContentAnswer(rs.getString(3));
		q.setCount(4);
		
		return q;
	}

}
