package service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dao.mapper.MyRowMapper;
import model.User;

@Service
public class RegistrationService {

	private JdbcTemplate jdbc;
	
	public RegistrationService() {
		jdbc = com.example.demo.DBASinglton.getDBASingltonInstance().getJdbcTemplate();
	}

	@SuppressWarnings("deprecation")
	public List<User> register(String name, String surname, String username, String password, String email) {
		
		@SuppressWarnings("unchecked")
		List<User> list = jdbc.query("select * from user where username=?", new Object[] { username } , new MyRowMapper());
		
		if(list.size() == 0) {
			jdbc.update("INSERT INTO User(Name, Surname, Username, Password, TypeOfUser, email) VALUES (?,?,?,?,?,?)", 
					new Object[] { name, surname, username, password, "user", email });
			
			list.add(new User());
			return list;
		}
		else
			return null;
	}
	
}
