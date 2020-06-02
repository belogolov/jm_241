package hiber.controller;

import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("authUsername")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/user")
	public String homePage(@ModelAttribute("authUsername") String username, ModelMap model) {
		model.addAttribute("user", userService.getUserByEmail(username));
		return "homeUser";
	}

}