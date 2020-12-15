package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

@SuppressWarnings("rawtypes")
public class QuestionAnswersJoinRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		QuestionAnswersJoinResultSetExtractor extractor = new QuestionAnswersJoinResultSetExtractor();
		return extractor.extractData(rs);
	}
}
