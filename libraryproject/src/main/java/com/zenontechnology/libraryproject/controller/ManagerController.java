package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.BooksService;
import com.zenontechnology.libraryproject.service.PublisherService;

@Controller
public class ManagerController {
	@Autowired
	private UserRepository usersService;

	@Autowired
	private BooksService booksService;

	@Autowired
	private PublisherService publishersService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@RequestMapping("/manager")
	public String shrForm(Model model) {
		List<Books> listBooks = booksService.listAll();
		model.addAttribute("listBooks", listBooks);
		return "./Views/Manager/index";
	}

	/**
	 * BOOK
	 * 
	 * 
	 **/
	@RequestMapping("/manager/book")
	public String shoUserForm(Model model) {
		List<Books> listBooks = booksService.listAll();
		model.addAttribute("listBooks", listBooks);
		return "./Views/Manager/bookIndex";
	}

	@RequestMapping("/manager/bookCreate")
	public String showNewUserForm(Model model) {
		Books book = new Books();
		model.addAttribute("book", book);

		return "./Views/Manager/bookCreate";
	}

	@RequestMapping("/manager/bookEdit/{id}")
	public ModelAndView showEPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/bookEdit");
		Books book = booksService.get(id);
		mav.addObject("book", book);
		book.setBookId(id);
		return mav;
	}

	@RequestMapping(value = "/manager/bookSave", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("book") Books book) {
		booksService.save(book);
		return "redirect:/manager/book";
	}

	@RequestMapping("/manager/bookDelete/{id}")
	public String deletct(@PathVariable(name = "id") Long id) {
		booksService.delete(id);
		return "redirect:/manager/book";
	}

	/**
	 * Publisher
	 * 
	 * 
	 **/
	@RequestMapping("/manager/publisher")
	public String shoUForm(Model model) {
		List<Publishers> listPublishers = publishersService.listAll();
		model.addAttribute("listPublishers", listPublishers);
		return "./Views/Manager/publisherIndex";
	}

	@RequestMapping("/manager/publisherCreate")
	public String showNewrForm(Model model) {
		Publishers publisher = new Publishers();
		model.addAttribute("publisher", publisher);

		return "./Views/Manager/publisherCreate";
	}

	@RequestMapping("/manager/publisherEdit/{id}")
	public ModelAndView shEPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/publisherEdit");
		Publishers publisher = publishersService.get(id);
		mav.addObject("publisher", publisher);
		publisher.setPublisherId(id);
		return mav;
	}

	@RequestMapping(value = "/manager/publisherSave", method = RequestMethod.POST)
	public String saUser(@ModelAttribute("publisher") Publishers publisher) {
		publishersService.save(publisher);
		return "redirect:/manager/publisher";
	}

	@RequestMapping("/manager/publisherDelete/{id}")
	public String detct(@PathVariable(name = "id") Long id) {
		publishersService.delete(id);
		return "redirect:/manager/publisher";
	}

}
