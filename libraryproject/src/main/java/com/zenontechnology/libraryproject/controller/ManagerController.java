package com.zenontechnology.libraryproject.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.function.FileUploadUtil;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.AuthorsService;
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

	@Autowired
	private AuthorsService authorsService;

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
	public String saveUser(@ModelAttribute("book") Books book, @RequestParam("image") MultipartFile multipartFile)
			throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		book.setBookImage(fileName);
		booksService.save(book);

		String uploadDir = "images/books-photos/" + book.getBookId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
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
	public String saUser(@ModelAttribute("publisher") Publishers publisher,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		publisher.setPublisherImage(fileName);
		publishersService.save(publisher);

		String uploadDir = "images/publishers-photos/" + publisher.getPublisherId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/manager/publisher";
	}

	@RequestMapping("/manager/publisherDelete/{id}")
	public String detct(@PathVariable(name = "id") Long id) {
		publishersService.delete(id);
		return "redirect:/manager/publisher";
	}

	/**
	 * Author
	 * 
	 * 
	 **/
	@RequestMapping("/manager/author")
	public String shoUF4rm(Model model) {
		List<Authors> listAuthors = authorsService.listAll();
		model.addAttribute("listAuthors", listAuthors);
		return "./Views/Manager/authorIndex";
	}

	@RequestMapping("/manager/authorCreate")
	public String shoewrForm(Model model) {
		Authors author = new Authors();
		model.addAttribute("author", author);

		return "./Views/Manager/authorCreate";
	}

	@RequestMapping("/manager/authorEdit/{id}")
	public ModelAndView shage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/authorEdit");
		Authors author = authorsService.get(id);
		mav.addObject("author", author);
		author.setAuthorId(id);
		return mav;
	}

	@RequestMapping(value = "/manager/authorSave", method = RequestMethod.POST)
	public String saUer(@ModelAttribute("author") Authors author, @RequestParam("image") MultipartFile multipartFile)
			throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		author.setAuthorImage(fileName);
		authorsService.save(author);

		String uploadDir = "images/authors-photos/" + author.getAuthorId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/manager/author";
	}

	@RequestMapping("/manager/authorDelete/{id}")
	public String dt(@PathVariable(name = "id") Long id) {
		authorsService.delete(id);
		return "redirect:/manager/author";
	}

	/**
	 * 
	 * User
	 * 
	 * 
	 **/
	@RequestMapping("/manager/user")
	public String showNrm(Model model) {
		List<Users> listUsers = usersService.listAll();
		model.addAttribute("listUsers", listUsers);
		return "./Views/Manager/userIndex";
	}

	@RequestMapping("/manager/userCreate")
	public String showUForm(Model model) {
		Users user = new Users();
		model.addAttribute("user", user);

		return "./Views/Manager/userCreate";
	}

	@RequestMapping(value = "/manager/userSave", method = RequestMethod.POST)
	public String savser(@ModelAttribute("users") Users user) {
		user.setUserRegisterDate(LocalDate.now());
		user.setUserPermission(true);
		String encodedPassword = encoder.encode(user.getUserPassword());

		user.setUserPassword(encodedPassword);
		usersService.save(user);
		return "redirect:/manager/user";
	}

	@RequestMapping("/manager/userEdit/{id}")
	public ModelAndView showEroductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/userEdit");
		Users user = usersService.getByUserId(id);
		mav.addObject("user", user);
		user.setUserId(id);
		return mav;
	}

	@RequestMapping("/manager/userDelete/{id}")
	public String deletroduct(@PathVariable(name = "id") Long id) {
		usersService.deleteById(id);
		return "redirect:/manager/user";
	}

}
