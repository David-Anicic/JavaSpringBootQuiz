package service;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dao.mapper.MyRowMapper;
import model.User;

@Service
public class LoginService {

	private JdbcTemplate jdbc;
	
	public LoginService() {
		jdbc = com.example.demo.DBASinglton.getDBASingltonInstance().getJdbcTemplate();
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<User> validateUser(String username, String password) {
		
		List<User> list = jdbc.query("SELECT * FROM user WHERE username=? and password=?", new Object[] { username, password }, new MyRowMapper());
		
		if(list.size() > 0) {
			jdbc.update("UPDATE User SET date=? WHERE username=?", new Object[] { new Date(),  username});
		}
		
		return list;
	}
}
