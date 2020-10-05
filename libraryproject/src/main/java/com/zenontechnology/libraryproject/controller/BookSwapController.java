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

import com.zenontechnology.libraryproject.entity.BookSwap;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.entity.UsersBook;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.BookSwapService;
import com.zenontechnology.libraryproject.service.UsersBookService;

@Controller
@RequestMapping("/bookSwap")
public class BookSwapController {

	@Autowired
	private BookSwapService bookSwapService;

	@Autowired
	private UsersBookService usersBookService;

	@Autowired
	UserRepository userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("usersbook") UsersBook usersbook, Principal principal) {
		System.out.print(usersbook);
		return "redirect:/usersbook";
	}

	/**
	 * 
	 * userin kitabı olup olmadığını kontrol gerek
	 * 
	 **/
	@RequestMapping("/swapCreate/{id}")
	public String showEditProductPage(@PathVariable(name = "id") Long id, Principal principal) {
		UsersBook usersBook = usersBookService.get(id);
		Users user = userService.getByUserName(principal.getName());
		int UserBookNumber = usersBookService.UserBookNumberByUserId(user.getUserId());

		BookSwap bookSwap = new BookSwap();

		if (UserBookNumber > 0) {

			// Sender
			bookSwap.setSenderId(user.getUserId());
			bookSwap.setSenderCheck(true);

			// Target
			bookSwap.setTargetId(usersBook.getUserId());
			bookSwap.setTargetBookId(usersBook.getUserBookId());

			// Status

			bookSwap.setSwapStatus(
					user.getUserEmail() + "Tarafından " + usersBook.getUserBookName() + " kitabı takas teklif edildi.");
			bookSwapService.save(bookSwap);
		}

		System.out.print(bookSwap.getSwapStatus());
		return "redirect:/usersbook";
	}

	@RequestMapping("")
	public String viewAuthorsPage(Model model, Principal principal) {
		Users user = userService.getByUserName(principal.getName());

		List<BookSwap> bookSwapSuccess = bookSwapService.getSuccessSwapByUserId(user.getUserId());
		model.addAttribute("bookSwapSuccess", bookSwapSuccess);
		return "./Views/BookSwap/index";
	}

}
