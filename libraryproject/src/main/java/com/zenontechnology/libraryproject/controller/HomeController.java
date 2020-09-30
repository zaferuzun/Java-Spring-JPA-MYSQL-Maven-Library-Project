package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.service.BooksService;

@Controller
public class HomeController {

	@Autowired
	private BooksService booksService;

	private int bookShow = 12;

	@RequestMapping("/")
	public String HomePage(Model model) {
		int allBookNumber = 0;
		allBookNumber = booksService.AllBookNumbers();
		List<Books> listBooks = null;
		if (bookShow > allBookNumber && allBookNumber != 0) {
			listBooks = booksService.listRandomBooks(allBookNumber);
		} else {
			listBooks = booksService.listRandomBooks(bookShow);
		}
		model.addAttribute("listBooks", listBooks);

		return "./Views/Home/home";
	}

	@RequestMapping("/index")
	public String HomesPage() {
		return "./Views/Home/index";
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
