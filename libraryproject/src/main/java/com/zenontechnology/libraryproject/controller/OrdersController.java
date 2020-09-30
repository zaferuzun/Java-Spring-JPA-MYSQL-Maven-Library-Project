package com.zenontechnology.libraryproject.controller;

import java.security.Principal;
import java.util.ArrayList;
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

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Orders;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.BooksService;
import com.zenontechnology.libraryproject.service.OrdersService;

@Controller
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	@Autowired
	BooksService booksService;
	@Autowired
	UserRepository userService;

	@RequestMapping(value = "/orders/username", method = RequestMethod.GET)
	@ResponseBody
	public String userName(Principal principal) {
		return principal.getName();
	}

	@RequestMapping("/orders")
	public String vieworderssPage(Model model, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		List<Books> listBooks = new ArrayList<Books>();
		List<Orders> orderList = ordersService.getOrdersByUserId(user.getUserId());
		for (Orders order : orderList) {
			Books book = new Books();
			book = booksService.get(order.getBookId());
			listBooks.add(book);
		}
		model.addAttribute("listBooks", listBooks);
		return "./Views/Orders/index";
	}

	@RequestMapping(value = "/orders/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("book") Books book, Principal principal) {
		Orders order = new Orders();
		Users user = userService.getByUserName(principal.getName());
		order.setBookId(book.getBookId());
		order.setUserId(user.getUserId());
		ordersService.save(order);
		return "redirect:/orders";
	}

	@RequestMapping(value = "/orders/checkBook", method = RequestMethod.GET)
	@ResponseBody
	public Boolean userHasBook(@RequestParam("BookId") int BookId, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		int userCheckBook = ordersService.CheckUserBook(user.getUserId(), (long) BookId);
		if (userCheckBook > 0) {
			return true;

		} else {
			return false;
		}
	}

	@RequestMapping(value = "/orders/delete2/{BookId}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable(name = "BookId") Long BookId, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		Long orderId = ordersService.getOrderId(user.getUserId(), BookId);
		ordersService.delete(orderId);
		return "redirect:/orders";
	}
}
