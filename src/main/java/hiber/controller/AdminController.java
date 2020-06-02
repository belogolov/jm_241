package hiber.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@GetMapping
	public String printUsers(ModelMap model) {
		model.addAttribute("listUsers", userService.listUsers());
		model.addAttribute("title", "All users");
		return "listOfUsers";
	}

	@GetMapping(value = "/add")
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "newUser";
	}

	@PostMapping(value = "/add")
	public String addUser(User user, ModelMap model) {
		userService.add(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/delete")
	public String deleteUser(@RequestParam("id") long id) {
		userService.delete(id);
		return "redirect:/admin";
	}

	@GetMapping(value = "/edit")
	public String editUser(@RequestParam("id") long id, ModelMap model) {
		User userById = userService.getUserById(id);
		model.addAttribute("user", userById);
		StringBuilder builder = new StringBuilder();
		for(Object r : userById.getRoles().toArray()) {
			builder.append((Role)r);
			builder.append(", ");
		}
		model.addAttribute("roles", builder.toString());
		model.addAttribute("title", "Edit user");
		return "editUser";
	}

	@PostMapping(value = "/edit")
	public String updateUser(User user, String roles) {
		userService.update(user, roles);
		return "redirect:/admin";
	}
}