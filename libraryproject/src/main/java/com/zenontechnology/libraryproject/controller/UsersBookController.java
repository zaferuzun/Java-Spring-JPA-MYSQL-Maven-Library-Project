package com.zenontechnology.libraryproject.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.entity.UsersBook;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.UsersBookService;

@Controller
@RequestMapping("/usersbook")
public class UsersBookController {

	@Autowired
	private UsersBookService usersBookService;

	@Autowired
	UserRepository userService;

	@RequestMapping("")
	public String viewbookssPage(Model model) {
		List<UsersBook> listUserBooks = usersBookService.listAll();
		model.addAttribute("listUserBooks", listUserBooks);
		return "./Views/UsersBook/index";
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		usersBookService.delete(id);
		return "redirect:/usersbook";
	}

	@RequestMapping("/create")
	public String showNewUserForm(Model model) {
		UsersBook usersbook = new UsersBook();
		model.addAttribute("usersbook", usersbook);

		return "./Views/UsersBook/create";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("usersbook") UsersBook usersbook, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		usersbook.setUserId(user.getUserId());
		usersBookService.save(usersbook);
		return "redirect:/usersbook";
	}

	@RequestMapping("/details/{id}")
	public String detaiage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
		UsersBook usersbook = usersBookService.get(id);
		model.addAttribute("usersbook", usersbook);
		return "./Views/UsersBook/details";
	}

	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/UsersBook/edit");
		UsersBook usersBook = usersBookService.get(id);
		usersBook.setUserBookId(id);

		mav.addObject("usersBook", usersBook);

		return mav;
	}

	@RequestMapping(value = "/userCheckBook", method = RequestMethod.GET)
	@ResponseBody
	public Boolean userHasBook(@RequestParam("UserBookId") int UserBookId, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		int userCheckBook = usersBookService.CheckHasUserBook(user.getUserId(), (long) UserBookId);
		if (userCheckBook > 0) {
			return true;

		} else {
			return false;
		}
	}

	@RequestMapping("/mybook")
	public String viewbooksddsPage(Model model, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		List<UsersBook> listUserBooks = usersBookService.getUsersBookByUserId(user.getUserId());
		model.addAttribute("listUserBooks", listUserBooks);
		return "./Views/UsersBook/mybooks";
	}

	/*
	 * @RequestMapping("/books/details/{id}") public String
	 * detailsPage(@PathVariable(name = "id") Long id, Model model) { Books book =
	 * booksService.get(id);
	 * 
	 * int allBookNumber = booksService.AllBookNumbers(); List<Books> listBooks =
	 * null; if (bookShow > allBookNumber && allBookNumber != 0) { listBooks =
	 * booksService.listRandomBooks(allBookNumber); } else { listBooks =
	 * booksService.listRandomBooks(bookShow); } model.addAttribute("listBooks",
	 * listBooks); model.addAttribute("book", book); model.addAttribute("listBooks",
	 * listBooks); return "./Views/Books/details"; }
	 * 
	 * /*
	 * 
	 * @RequestMapping("/getBooks") public Books placeOrder(@RequestBody
	 * OrderRequest request) { return booksService.save(request.getBooks); }
	 */
	/*
	 * @RequestMapping(value = "/getBooks", method = RequestMethod.GET)
	 * public @ResponseBody List<Books> getAuthors() { List<Books> books =
	 * booksService.listAll(); return books; }
	 */
	/**//*
		 * @RequestMapping(value = "/getDeneme", method = RequestMethod.GET)
		 * public @ResponseBody int getDeneme() { int books =
		 * booksService.findAllActiveUsers();
		 * 
		 * return books; }
		 * 
		 * @RequestMapping(value = "/getDeneme2", method = RequestMethod.GET)
		 * public @ResponseBody int getDeneme2() { int books =
		 * booksService.AuthorBookNumber((long) 6);
		 * 
		 * return books; }
		 * 
		 * @RequestMapping(value = "/getDeneme3", method = RequestMethod.GET)
		 * public @ResponseBody int getDeneme3() { int books =
		 * booksService.PublisherBookNumber((long) 3);
		 * 
		 * return books; }
		 * 
		 * /
		 **//*
			 * @RequestMapping("/books/create") public String showNewUserForm(Model model) {
			 * Books book = new Books(); model.addAttribute("book", book);
			 * 
			 * return "./Views/Books/create"; }
			 * 
			 * @RequestMapping(value = "/books/save", method = RequestMethod.POST) public
			 * String saveUser(@ModelAttribute("book") Books book) {
			 * booksService.save(book); return "redirect:/books"; }
			 * 
			 * @RequestMapping(value = "/books2/save2", method = RequestMethod.GET) public
			 * String saveUser2() { for (int i = 0; i < 10; i++) { Books book2 = new
			 * Books(); book2.setAuthorId((long) 11); book2.setBookDefination("Defination" +
			 * i); book2.setBookIsbnNo("ISBN" + i); book2.setBookLanguage("Dil" + i);
			 * book2.setBookName("Kitap" + i); book2.setBookReleaseDate(LocalDate.now());
			 * book2.setBookSeriesName("Seri" + i); book2.setBookSubName("Alt Adı" + i);
			 * book2.setPublisherId((long) 15); booksService.save(book2); } for (int i = 10;
			 * i < 20; i++) { Books book2 = new Books(); book2.setAuthorId((long) 18);
			 * book2.setBookDefination("Defination" + i); book2.setBookIsbnNo("ISBN" + i);
			 * book2.setBookLanguage("Dil" + i); book2.setBookName("Kitap" + i);
			 * book2.setBookReleaseDate(LocalDate.now()); book2.setBookSeriesName("Seri" +
			 * i); book2.setBookSubName("Alt Adı" + i); book2.setPublisherId((long) 21);
			 * booksService.save(book2); }
			 * 
			 * return "redirect:/books"; }
			 * 
			 * @RequestMapping("/books/edit/{id}") public ModelAndView
			 * showEditProductPage(@PathVariable(name = "id") Long id) { ModelAndView mav =
			 * new ModelAndView("./Views/Books/edit"); Books book = booksService.get(id);
			 * mav.addObject("book", book); book.setBookId(id); return mav; }
			 * 
			 * @RequestMapping("/books/delete/{id}") public String
			 * deleteProduct(@PathVariable(name = "id") Long id) { booksService.delete(id);
			 * return "redirect:/books"; }
			 */
}
