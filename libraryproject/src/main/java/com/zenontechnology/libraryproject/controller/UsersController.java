package com.zenontechnology.libraryproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.repository.UserRepository;

@Controller
public class UsersController {

	@Autowired
	private UserRepository usersService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@RequestMapping("/login")
	public String viewusersPage() {
		return "./Views/Home/login";
	}

	@RequestMapping("/loginSuccess")
	public String successUsersPage() {
		return "redirect:/books";
	}

	@RequestMapping("/logoutSuccess")
	public String logoutUsersPage() {
		return "redirect:/";
	}

	@RequestMapping("/users")
	public String viewusersPage(Model model) {
		List<Users> listUsers = usersService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "./Views/Users/index";
	}

	@RequestMapping("/register")
	public String showNewUserForm(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);

		return "./Views/Home/register";
	}
	/*
	 * create için başka bir save yaz
	 * 
	 * @RequestMapping("/users/create") public String UserForm(Model model) { Users
	 * user = new Users(); model.addAttribute("user", user);
	 * 
	 * return "./Views/Users/create"; }
	 */

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("users") Users user) {
		user.setUserRegisterDate(LocalDate.now());
		user.setUserPermission(true);
		String encodedPassword = encoder.encode(user.getUserPassword());

		user.setUserPassword(encodedPassword);
		usersService.save(user);
		// Rol ekleme
		/*
		 * UserRoles userRoles = new UserRoles(); userRoles.setUserId(user.getUserId());
		 * userRoles.setRoleId((long) 1); userRolesRepository.save(userRoles);
		 */
		return "redirect:/login";
	}

}
