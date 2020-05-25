package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@SessionAttributes("loggedUser")
public class LoginController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String loginUser(ModelMap model) {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView checkUser(@RequestParam("email") String login, @RequestParam("password") String password) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("loggedUser", userService.validUser(login, password));
		modelAndView.setViewName("redirect:/admin");
		return modelAndView;
	}

}