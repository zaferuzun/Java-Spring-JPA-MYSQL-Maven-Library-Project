package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/**
	 * Yayın Evi işlemleri, Temel listelenme, detayları ve kitapları şeklindedir.
	 * Yayın Evi sekmesi için kullanıcılar arasında bir ayrım vardır, USER---->
	 * Yayın Evi temel gösterimi,detaylar gibi işlemlerini yapabilmektedir. EDITOR
	 * ---> USER in yaptıklarına ek olarak Editor panelinden düzeltmelerde
	 * bulunabilir. ADMIN ----> Admin Panelinden Tüm işlemleri yapabilmektedir.
	 */

	/**
	 * Temel olarak Yayın Evi gösterimidir.
	 */
	/***********************
	 * http://localhost:8080/publishers
	 *******************************/
	@RequestMapping("/publishers")
	public String publishersIndex(Model model) {
		List<Publishers> listPublishers = publishersService.listAll();
		model.addAttribute("listPublishers", listPublishers);
		return "./Views/Publishers/index";
	}

	/**
	 * Temel olarak Yayın Evi detaylarının ve kitaplarının bulunduğu sayfadır.
	 */
	/*********** http://localhost:8080/publishers/details/{id} *************/
	@RequestMapping("/publishers/details/{id}")
	public String publishersDetails(@PathVariable(name = "id") Long id, Model model) {
		Publishers publisher = publishersService.get(id);
		List<Books> listBooks = booksService.listBooksByPublisherId(publisher.getPublisherId());
		model.addAttribute("publisher", publisher);
		model.addAttribute("listBooks", listBooks);

		return "./Views/Publishers/details";
	}

	/*
	 * @RequestMapping("/publishers/create") public String showNewUserForm(Model
	 * model) { Publishers publisher = new Publishers();
	 * model.addAttribute("publisher", publisher);
	 * 
	 * return "./Views/Publishers/create"; }
	 * 
	 * @RequestMapping(value = "/publishers/save", method = RequestMethod.POST)
	 * public String saveUser(@ModelAttribute("publisher") Publishers publisher) {
	 * publishersService.save(publisher);
	 * 
	 * return "redirect:/publishers"; }
	 * 
	 * @RequestMapping("/publishers/edit/{id}") public ModelAndView
	 * showEditProductPage(@PathVariable(name = "id") Long id) { ModelAndView mav =
	 * new ModelAndView("./Views/Publishers/edit"); Publishers publisher =
	 * publishersService.get(id); mav.addObject("publisher", publisher);
	 * publisher.setPublisherId(id); return mav; }
	 * 
	 * @RequestMapping("/publishers/delete/{id}") public String
	 * deleteProduct(@PathVariable(name = "id") Long id) {
	 * publishersService.delete(id); return "redirect:/publishers"; }
	 */
	/**
	 * Tüm yayın evlerinin response olarak döndüğü çağrıdır. Projede Ajax çağrısına
	 * cevap vermektedir.
	 */
	/*********** http://localhost:8080/getPublishers *************/
	@RequestMapping(value = "/getPublishers", method = RequestMethod.GET)
	public @ResponseBody List<Publishers> getPublishers() {
		List<Publishers> publishers = publishersService.listAll();

		return publishers;
	}

	/**
	 * Tüm yayın evinin response olarak döndüğü çağrıdır. Projede Ajax çağrısına
	 * cevap vermektedir.
	 */
	/*********** http://localhost:8080/getPublisher/{id} *************/
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
