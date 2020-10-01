package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.service.AuthorsService;
import com.zenontechnology.libraryproject.service.BooksService;

@Controller
public class AuthorsController {

	@Autowired
	private AuthorsService authorsService;

	@Autowired
	private BooksService booksService;

	// AUTHORS LIST
	/*********** http://localhost:8080/authors *************/
	/******************************************************/
	@RequestMapping("/authors")
	public String viewAuthorsPage(Model model) {
		List<Authors> listAuthors = authorsService.listAll();
		model.addAttribute("listAuthors", listAuthors);
		return "./Views/Authors/index";
	}

	// AUTHOR DETAILS
	/*********** http://localhost:8080/authors/details/{id} *************/
	/******************************************************/
	/** LIST BOOKS BY AUTHOR ID WITH BOOKS SERVICE **/
	@RequestMapping("/authors/details/{id}")
	public String detailssPage(@PathVariable(name = "id") Long id, Model model) {
		Authors author = authorsService.get(id);
		List<Books> listBooks = booksService.listBooksByAuthorId(author.getAuthorId());
		model.addAttribute("author", author);
		model.addAttribute("listBooks", listBooks);

		return "./Views/Authors/details";
	}

//deneme
	/*
	 * @RequestMapping(value = "/getAuthors", method = RequestMethod.GET) public
	 * List<Authors> deneme() { List<Authors> listAuthors =
	 * authorsService.listAll(); return listAuthors; }
	 */
	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
	public @ResponseBody List<Authors> getAuthors() {
		List<Authors> authors = authorsService.listAll();

		return authors;
	}

	@RequestMapping(value = "/getAuthor/{id}", method = RequestMethod.GET)
	public @ResponseBody Authors getAuthorById(@PathVariable("id") Long id) {
		Authors author = authorsService.get(id);
		return author;
	}

//deneme	
	@RequestMapping("/authors/create")
	public String showNewUserForm(Model model) {
		Authors author = new Authors();
		model.addAttribute("author", author);

		return "./Views/Authors/create";
	}

	@RequestMapping(value = "/authors/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("author") Authors author) {
		authorsService.save(author);

		return "redirect:/authors";
	}

	@RequestMapping("/authors/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Authors/edit");
		Authors author = authorsService.get(id);
		mav.addObject("author", author);
		author.setAuthorId(id);
		return mav;
	}

	@RequestMapping("/authors/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		authorsService.delete(id);
		return "redirect:/authors";
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * **/
	@RequestMapping(value = "/authors/save2", method = RequestMethod.GET)
	public String saveUser2() {
		for (int i = 0; i < 20; i++) {
			Authors author = new Authors();
			author.setAuthorName("name " + i);
			author.setAuthorSurname("surname " + i);
			author.setAuthorContact("iletişim " + i);
			author.setAuthorDefination("açıklama " + i);

			authorsService.save(author);
		}
		return "redirect:/books";
	}
}
