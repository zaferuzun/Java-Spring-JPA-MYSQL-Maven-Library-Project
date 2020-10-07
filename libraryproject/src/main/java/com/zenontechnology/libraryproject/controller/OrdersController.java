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

	/**
	 * Kitap alma işlemleri, Projenin gidişatına göre kitap alma işlemleri bölümü
	 * aslında online bir kütüphane olarak degiştirilmiştir.Kullanıcı kütüphaneden
	 * kitap alabilir ve aldığı kitabı iade edebilir.
	 */

	/**
	 **** Değiştirilecek****** Kitap alma durumunda ajax çağrısı ile giriş yapmış
	 * kullanıcının email adresi getiriliyor.
	 */
	/*********** http://localhost:8080/orders/username *************/
	@RequestMapping(value = "/orders/username", method = RequestMethod.GET)
	@ResponseBody
	public String getUserName(Principal principal) {
		return principal.getName();
	}

	/**
	 * Giriş yapan kullanıcı tarafından alınmış kitaplar listeleniyor.
	 */
	/*********** http://localhost:8080/orders *************/
	@RequestMapping("/orders")
	public String ordersIndex(Model model, Principal principal) {
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

	/**
	 * Giriş yapan kullanıcı tarafından alınan kitapların kayıt edilmesi
	 */
	/*********** http://localhost:8080/orders/save *************/
	@RequestMapping(value = "/orders/save", method = RequestMethod.POST)
	public String ordersSave(@ModelAttribute("book") Books book, Principal principal) {
		Orders order = new Orders();
		Users user = userService.getByUserName(principal.getName());
		order.setBookId(book.getBookId());
		order.setUserId(user.getUserId());
		ordersService.save(order);
		return "redirect:/orders";
	}

	/**
	 * Giriş yapan kullanıcının baktığı kütüphane kitabına sahip olup olmadığının
	 * tespit edilip Ajax Çağrısında kullanması için yazılmış çağrıdır.
	 */
	/*********** http://localhost:8080/orders/checkBook *************/
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

	/**
	 * Kullanıcı tarafından alınmış olan kitabın iadesi işlemidir.
	 */
	/*********** http://localhost:8080/orders/delete2/BookId *************/
	@RequestMapping(value = "/orders/delete2/{BookId}", method = RequestMethod.GET)
	public String orderDelete(@PathVariable(name = "BookId") Long BookId, Principal principal) {
		Users user = userService.getByUserName(principal.getName());
		Long orderId = ordersService.getOrderId(user.getUserId(), BookId);
		ordersService.delete(orderId);
		return "redirect:/orders";
	}
}
