package com.zenontechnology.libraryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

	@RequestMapping("/login")
	public String viewusersPage() {
		return "./Views/Users/login";
	}
}
