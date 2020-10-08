package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

	// deneme
	/**
	 * Yazar işlemleri, Temel listelenme, detayları ve kitapları şeklindedir. Yazar
	 * sekmesi için kullanıcılar arasında bir ayrım vardır, USER----> Yazarın temel
	 * gösterimi,detaylar gibi işlemlerini yapabilmektedir. EDITOR ---> USER in
	 * yaptıklarına ek olarak Editor panelinden düzeltmelerde bulunabilir. ADMIN
	 * ----> Admin Panelinden Tüm işlemleri yapabilmektedir.
	 */

	/**
	 * Temel olarak yazarların gösterimidir.
	 */
	/***********************
	 * http://localhost:8080/authors
	 *******************************/
	@RequestMapping("/authors")
	public String authorsIndex(Model model) {
		List<Authors> listAuthors = authorsService.listAll();
		model.addAttribute("listAuthors", listAuthors);
		return "./Views/Authors/index";
	}

	/**
	 * Temel olarak yazarların detaylarının ve kitaplarının bulunduğu sayfadır.
	 */
	/*********** http://localhost:8080/authors/details/{id} *************/
	@RequestMapping("/authors/details/{id}")
	public String authorDetails(@PathVariable(name = "id") Long id, Model model) {
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

	/**
	 * Tüm yazarların response olarak döndüğü çağrıdır. Projede Ajax çağrısına cevap
	 * vermektedir.
	 */
	/*********** http://localhost:8080/getAuthors *************/
	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
	public @ResponseBody List<Authors> getAuthors() {
		List<Authors> authors = authorsService.listAll();
		return authors;
	}

	/**
	 * Tüm yazarın response olarak döndüğü çağrıdır. Projede Ajax çağrısına cevap
	 * vermektedir.
	 */
	/*********** http://localhost:8080/getAuthor/{id} *************/
	@RequestMapping(value = "/getAuthor/{id}", method = RequestMethod.GET)
	public @ResponseBody Authors getAuthorById(@PathVariable("id") Long id) {
		Authors author = authorsService.get(id);
		return author;
	}

	/*
	 * @RequestMapping("/authors/create") public String showNewUserForm(Model model)
	 * { Authors author = new Authors(); model.addAttribute("author", author);
	 * 
	 * return "./Views/Authors/create"; } /*
	 * 
	 * @RequestMapping(value = "/authors/save", method = RequestMethod.POST) public
	 * String saveUser(@ModelAttribute("author") Authors author) {
	 * authorsService.save(author);
	 * 
	 * return "redirect:/authors"; }
	 * 
	 * @RequestMapping("/authors/edit/{id}") public ModelAndView
	 * showEditProductPage(@PathVariable(name = "id") Long id) { ModelAndView mav =
	 * new ModelAndView("./Views/Authors/edit"); Authors author =
	 * authorsService.get(id); mav.addObject("author", author);
	 * author.setAuthorId(id); return mav; }
	 * 
	 * @RequestMapping("/authors/delete/{id}") public String
	 * deleteProduct(@PathVariable(name = "id") Long id) {
	 * authorsService.delete(id); return "redirect:/authors"; }
	 * 
	 * 
	 * @RequestMapping(value = "/authors/save2", method = RequestMethod.GET) public
	 * String saveUser2() { for (int i = 0; i < 20; i++) { Authors author = new
	 * Authors(); author.setAuthorName("name " + i);
	 * author.setAuthorSurname("surname " + i); author.setAuthorContact("iletişim "
	 * + i); author.setAuthorDefination("açıklama " + i);
	 * 
	 * authorsService.save(author); } return "redirect:/books"; }
	 */
}
