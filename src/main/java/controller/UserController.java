package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService us;
	
	@RequestMapping(value = "/all",  method=RequestMethod.GET)
	public List<User> getAll() {
		return us.getAll();
	}
	
	@RequestMapping("/allAnswered")
	public List<User> getAllAnswered() {
		return us.getAllAnswered();
	}
	
	@RequestMapping("/allCorrectAnswered")
	public List<User> getAllCorrectAnswered() {
		return us.getAllCorrectAnswered();
	}
	
	@RequestMapping("/sortedUsersByPerformance")
	public List<User> getAllSortedUsersByPerformance() {
		return us.getAllSortedUsersByPerformance();
	}
}
