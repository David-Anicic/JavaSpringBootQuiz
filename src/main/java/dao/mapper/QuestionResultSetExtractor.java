package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.Question;

@SuppressWarnings("rawtypes")
public class QuestionResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		Question q = new Question();
		q.setContent(rs.getString(1));
		q.setCount(rs.getInt(2));
		
		return q;
	}
}
