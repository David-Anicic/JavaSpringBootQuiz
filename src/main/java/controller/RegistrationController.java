package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.RegistrationService;

@Controller
public class RegistrationController {

	RegistrationService service;
	
	public RegistrationController() {
		service = new RegistrationService();
	}
	
	@GetMapping("/register")
	public ModelAndView showRegisterPage() {
		return new ModelAndView("register");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(ModelMap model, @RequestParam String name, @RequestParam String surname, 
			@RequestParam String username, @RequestParam String password, @RequestParam String email) {
		
		List<User> list = service.register(name, surname, username, password, email);
		
		if(list.size() > 0) {
			return "redirect:/login";
		}
		else
			return "redirect:/register";
	}
}
