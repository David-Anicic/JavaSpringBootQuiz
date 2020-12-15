package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.LoginService;

@Controller
@SessionAttributes("name")
public class LoginController {

	LoginService service;
	
	public LoginController() {
		service = new LoginService();
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getLogin() {
		return "redirect:/login";
	}
	
	@GetMapping(value="/logout")
	public String logout(HttpSession session) {
		session.setAttribute("logedin", null);
		return "redirect:/login";
	}
	
	@RequestMapping(value="/login")
	public String showLoginPage(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String showWelcomePage(ModelMap model, @RequestParam String username, @RequestParam String password, HttpSession session) {
		
		List<User> list = service.validateUser(username, password);
		if(list.size() > 0) {
			User user = list.get(0);
			
			session.setAttribute("logedin", "yes");
			if(user.getTypeOfUser().contentEquals("admin")) {
							
				session.setAttribute("tof", "admin");
				session.setAttribute("username", username);
				return "redirect:/admin";
			}
			else {
				session.setAttribute("tof", "user");
				session.setAttribute("username", username);
				session.setAttribute("userid", user.getUserID());
				return "redirect:/user";
			}
		}

		model.put("errorMessage", "Invalid Credentials");
		return "login";
	}
	
	@GetMapping("/admin")
	public ModelAndView getAdminPage() {
		return new ModelAndView("admin");
	}
	
	@GetMapping("/user")
	public ModelAndView getUserPage() {
		return new ModelAndView("user");
	}
}
