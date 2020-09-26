package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.service.BooksService;

@Controller
public class BooksController {

	@Autowired
	private BooksService booksService;

	@RequestMapping("/books")
	public String viewbookssPage(Model model) {
		List<Books> listBooks = booksService.listAll();
		model.addAttribute("listBooks", listBooks);
		return "./Views/Books/index";
	}

	@RequestMapping("/books/create")
	public String showNewUserForm(Model model) {
		Books book = new Books();
		model.addAttribute("book", book);

		return "./Views/Books/create";
	}

	@RequestMapping(value = "/books/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("book") Books book) {
		booksService.save(book);

		return "redirect:/books";
	}

	@RequestMapping("/books/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Books/edit");
		Books book = booksService.get(id);
		mav.addObject("book", book);
		book.setBookId(id);
		return mav;
	}

	@RequestMapping("/books/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		booksService.delete(id);
		return "redirect:/books";
	}
}
