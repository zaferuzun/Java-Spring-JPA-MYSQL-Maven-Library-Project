package com.zenontechnology.libraryproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.service.UsersService;

@Controller
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("/users")
	public String viewusersPage(Model model) {
		List<Users> listUsers = usersService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "./Views/users/index";
	}

	@RequestMapping("/users/create")
	public String showNewUserForm(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);

		return "./Views/users/create";
	}

	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") Users user) {
		user.setUserRegisterDate(LocalDate.now());
		usersService.save(user);

		return "redirect:/users";
	}

	@RequestMapping("/users/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Users/edit");
		Users user = usersService.get(id);
		mav.addObject("user", user);
		user.setUserId(id);
		return mav;
	}

	@RequestMapping("/users/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		usersService.delete(id);
		return "redirect:/users";
	}
}
