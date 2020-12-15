package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.User;

@SuppressWarnings("rawtypes")
public class UsersAnsweredSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		User u = new User();
		u.setUserID(rs.getInt(1));
		u.setName(rs.getString(2));
		u.setSurname(rs.getString(3));
		u.setUsername(rs.getString(4));
		
		return u;
	}

}
