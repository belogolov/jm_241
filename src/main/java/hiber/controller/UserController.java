package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes("loggedUser")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homePage() {
		return "redirect:/admin";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("loggedUser") User user, ModelMap model) {
		model.addAttribute("user", user);
		return "homeUser";
	}


}