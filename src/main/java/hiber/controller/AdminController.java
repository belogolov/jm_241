package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printUsers(ModelMap model) {
		model.addAttribute("listUsers", userService.listUsers());
		model.addAttribute("title", "All users");
		return "listOfUsers";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(User user, ModelMap model) {
		userService.add(user);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteUser(@RequestParam("id") long id) {
		userService.delete(id);
		return "redirect:/admin";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editUser(@RequestParam("id") long id, ModelMap model) {
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("title", "Edit user");
		return "editUser";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateUser(User user, ModelMap model) {
		userService.update(user);
		return "redirect:/admin";
	}
}