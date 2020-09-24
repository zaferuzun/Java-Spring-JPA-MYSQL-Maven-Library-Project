package com.zenontechnology.libraryproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String HomePage() {
		return "./Views/Home/home";
	}

	@RequestMapping("/contact")
	public String ContactPage() {
		return "./Views/Home/contact";
	}

	@RequestMapping("/about")
	public String AboutPage() {
		return "./Views/Home/about";
	}

}
