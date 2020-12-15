package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.example.demo.GetSetExtractorFactory;

@SuppressWarnings("rawtypes")
public class MyRowMapper implements RowMapper {

	private String type;
	
	public MyRowMapper() {
		type = "";
	}
	
	public MyRowMapper(String type) {
		this.type = type;
	}
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		GetSetExtractorFactory setExtractorFactory = new GetSetExtractorFactory();
		
		ResultSetExtractor extractor = setExtractorFactory.getExtractor(type);

		return extractor.extractData(rs);
	}
	
	public String getType() {
		return type;
	}
}
