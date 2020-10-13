package com.zenontechnology.libraryproject.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value = "/userCheck/{email}", method = RequestMethod.GET)
	public @ResponseBody int getAuthorById(@PathVariable("email") String email) {
		int userCheck = usersService.userCheck(email);
		return userCheck;
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
	public String saveUser(@ModelAttribute("users") Users user, RedirectAttributes redirectAttributes) {
		int userCheck = usersService.userCheck(user.getUserEmail());
		System.out.print(userCheck);
		if (userCheck > 0) {
			redirectAttributes.addFlashAttribute("EmailError", "Failed");
			return "redirect:/register";
		} else {
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

}
