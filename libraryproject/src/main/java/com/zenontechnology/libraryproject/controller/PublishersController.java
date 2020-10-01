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

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.service.BooksService;
import com.zenontechnology.libraryproject.service.PublisherService;

@Controller
public class PublishersController {

	@Autowired
	private PublisherService publishersService;

	@Autowired
	private BooksService booksService;

	// PUBLISHERS LIST
	/*********** http://localhost:8080/publishers *************/
	/******************************************************/
	@RequestMapping("/publishers")
	public String viewpublishersPage(Model model) {
		List<Publishers> listPublishers = publishersService.listAll();
		model.addAttribute("listPublishers", listPublishers);
		return "./Views/Publishers/index";
	}

	// PUBLISHER DETAILS
	/*********** http://localhost:8080//publishers/details/{id} *************/
	/******************************************************/
	/** LIST BOOKS BY PUBLISHER ID WITH BOOKS SERVICE **/
	@RequestMapping("/publishers/details/{id}")
	public String detailssPage(@PathVariable(name = "id") Long id, Model model) {
		Publishers publisher = publishersService.get(id);
		List<Books> listBooks = booksService.listBooksByPublisherId(publisher.getPublisherId());
		model.addAttribute("publisher", publisher);
		model.addAttribute("listBooks", listBooks);

		return "./Views/Publishers/details";
	}

	@RequestMapping("/publishers/create")
	public String showNewUserForm(Model model) {
		Publishers publisher = new Publishers();
		model.addAttribute("publisher", publisher);

		return "./Views/Publishers/create";
	}

	@RequestMapping(value = "/publishers/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("publisher") Publishers publisher) {
		publishersService.save(publisher);

		return "redirect:/publishers";
	}

	@RequestMapping("/publishers/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Publishers/edit");
		Publishers publisher = publishersService.get(id);
		mav.addObject("publisher", publisher);
		publisher.setPublisherId(id);
		return mav;
	}

	@RequestMapping("/publishers/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		publishersService.delete(id);
		return "redirect:/publishers";
	}

	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET)
	public @ResponseBody List<Publishers> getPublishers() {
		List<Publishers> publishers = publishersService.listAll();

		return publishers;
	}

	@RequestMapping(value = "/getPublisher/{id}", method = RequestMethod.GET)
	public @ResponseBody Publishers getPublisherById(@PathVariable("id") Long id) {
		Publishers publisher = publishersService.get(id);
		return publisher;
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
	@RequestMapping(value = "/publishers/save2", method = RequestMethod.GET)
	public String saveUser2() {
		for (int i = 0; i < 20; i++) {
			Publishers publisher = new Publishers();
			publisher.setPublisherName("name " + i);
			publisher.setPublisherContact("iletişim " + i);
			publisher.setPublisherDefination("açıklama " + i);

			publishersService.save(publisher);
		}
		return "redirect:/books";
	}
}
