package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import model.User;

@SuppressWarnings("rawtypes")
public class UserResultSetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		User user = new User();
		user.setUserID(rs.getInt(1));
		user.setName(rs.getString(2));
		user.setSurname(rs.getString(3));
		user.setUsername(rs.getString(4));
		user.setPassword(rs.getString(5));
		user.setDate(rs.getDate(6));
		user.setTypeOfUser(rs.getString(7));
		
		return user;
	}
}
