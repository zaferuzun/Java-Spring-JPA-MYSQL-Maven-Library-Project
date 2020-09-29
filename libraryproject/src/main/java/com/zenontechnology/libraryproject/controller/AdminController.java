package com.zenontechnology.libraryproject.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.repository.UserRepository;

@Controller
public class AdminController {
	@Autowired
	private UserRepository usersService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@RequestMapping("/users/create")
	public String showNewUserForm(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);

		return "./Views/Users/create";
	}

	@RequestMapping(value = "/users/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("users") Users user) {
		user.setUserRegisterDate(LocalDate.now());
		user.setUserPermission(true);
		String encodedPassword = encoder.encode(user.getUserPassword());

		user.setUserPassword(encodedPassword);
		usersService.save(user);
		return "redirect:/login";
	}

	@RequestMapping("/users/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Users/edit");
		Users user = usersService.getByUserId(id);
		mav.addObject("user", user);
		user.setUserId(id);
		return mav;
	}

	@RequestMapping("/users/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		usersService.deleteById(id);
		return "redirect:/users";
	}
}
