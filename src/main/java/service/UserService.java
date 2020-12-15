package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import dao.mapper.MyRowMapper;
import model.User;

@Service
public class UserService {

	private JdbcTemplate jdbc;
	
	public UserService() {
		jdbc = com.example.demo.DBASinglton.getDBASingltonInstance().getJdbcTemplate();
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		
		return jdbc.query("select * from user where TypeOfUser not like 'admin'", new MyRowMapper());
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllAnswered() {
		return jdbc.query("select u.userid, u.name, u.surname, u.username\r\n" + 
				"from questionsusers qu join user u on qu.userid = u.userid and u.typeofuser like 'user'\r\n" + 
				"group by u.userid, u.name, u.surname, u.username\r\n" + 
				"having count(u.userid) = (select count(*) from question)", new MyRowMapper("UsersAnsweredSetExtractor"));
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllCorrectAnswered() {
		
		return jdbc.query("select userid, name, surname, username\r\n" + 
				"from user\r\n" + 
				"where typeofuser like 'user' and userid not in\r\n" + 
				"(select qu.userid\r\n" + 
				"from questionsusers qu left join questionanswer qa on qu.questionid=qa.questionid\r\n" + 
				"		and qu.answer=qa.content and qa.correct=1\r\n" + 
				"where qa.correct is null)", new MyRowMapper("UsersAnsweredSetExtractor"));
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllSortedUsersByPerformance() {
		
		List<User> trueAnswers = jdbc.query("select qu.userid, u.name, u.surname, u.username, count(*) count\r\n" + 
				"from questionsusers qu join questionanswer qa on\r\n" + 
				"	qu.questionid=qa.questionid and qa.content=qu.answer and\r\n" + 
				"	qa.correct=1 join user u on qu.userid=u.userid and u.typeofuser like 'user'\r\n" + 
				"where qu.answer not like ''\r\n" + 
				"group by qu.userid, u.name, u.surname, u.username\r\n" + 
				"order by count desc", new MyRowMapper("UsersSortedSetExtractor"));
		
		List<User> falseAnswers = jdbc.query("select qu.userid, u.name, u.surname, u.username, count(*) count\r\n" + 
				"from questionsusers qu join questionanswer qa on qu.questionid=qa.questionid\r\n" + 
				"	and ((qa.content=qu.answer and qa.correct=0) or (qu.answer like '')) join user u on qu.userid=u.userid\r\n" + 
				"group by qu.userid, u.name, u.surname, u.username\r\n" + 
				"order by username desc", new MyRowMapper("UsersSortedSetExtractor"));
		
		int i = 0;
		int n;
		if (trueAnswers.size() < falseAnswers.size())
			n = trueAnswers.size();
		else if (trueAnswers.size() > falseAnswers.size())
			n = falseAnswers.size();
		else
			n = trueAnswers.size();
		
		List<User> finalList = new ArrayList<User>(); 
		for (User user : trueAnswers) {
			user.setCount(user.getCount()-falseAnswers.get(i).getCount());
			user.setCount(user.getCount()*10);
			finalList.add(user);
			
			i++;
			if(i+1 > n)
				break;
		}
		
		for (int j=i;j < trueAnswers.size(); j++) {
			User userpom = trueAnswers.get(j); 
			userpom.setCount(userpom.getCount()*10);
			finalList.add(userpom);
		}
		
		for (int j = i; j < falseAnswers.size(); j++) {
			User userpom = falseAnswers.get(j); 
			userpom.setCount(userpom.getCount()*10);
			finalList.add(userpom);
		}
		
		User u1, u2, pom;
		for (i = 0; i < finalList.size()-1; i++)
			for(int j=i+1;j<finalList.size(); j++) {
				u1 = finalList.get(i);
				u2 = finalList.get(j);
				
				if(u1.getCount() < u2.getCount()) {
					pom = u1;
					finalList.set(i, u2);
					finalList.set(j, pom);
				}
			}
		
		return finalList;
	}
}
