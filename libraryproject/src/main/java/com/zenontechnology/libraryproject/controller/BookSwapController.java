package com.zenontechnology.libraryproject.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zenontechnology.libraryproject.dto.UserSwapDto;
import com.zenontechnology.libraryproject.entity.BookSwap;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.entity.UsersBook;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.BookSwapService;
import com.zenontechnology.libraryproject.service.MapService;
import com.zenontechnology.libraryproject.service.UsersBookService;

@Controller
@RequestMapping("/bookswap")
public class BookSwapController {

	@Autowired
	private BookSwapService bookSwapService;

	@Autowired
	private UsersBookService usersBookService;

	@Autowired
	UserRepository userService;

	@Autowired
	MapService mapService;

	/*************************************************************************************
	 * Takas işlemi; Takas edilmek istenen kitap seçilir ve takas isteği
	 * gönderilir.Bu kısımda takas isteği gönderen kişinin, Kitabı olup olmadığına
	 * bakılır ve UserId, istenilen kitap, hedef UserId ve bir açıklama eklenerek
	 * talep gönderilmiş, veritabanına eklenmiş olur. Talebi alan user talebi uygun
	 * görmediği taktirde talebi iptal edebilir. Eğer kitap seçip talebe devam etmek
	 * isterse, talep gelen kişiden bir kitap seçer, Seçilen kitap Idsi ile karşı
	 * talep gönderilir. İlk talep oluşturan kişiye, karşı kişinin istediği kitap
	 * ile birlikte talep gelir, bu talebi onaylayabilir veya reddedebilir.
	 * 
	 ************************************************************************************
	 * Projenin ilerleyen aşamalarında düşünülen şudur ki, kullanıcı kitap ekleme
	 * sayfasında bir adres belirtmesidir. Belirtilen adres ile talep edecek
	 * kullanıcı adresi arasında bir api yardımı ile orta yol(meetway) bulunacak
	 * buna göre kitaplarını kullanıcılar kendi arasında takaslayabilecektir.
	 * 
	 * *********************************************************************************
	 * 
	 * Bu durumlar ADMIN, USER, EDITOR için geçerlidir. Lakin Admin ve Editor
	 * panellerinden bu işlemlerin editlenmesi gibi işlemler bulunmamaktadır.
	 * 
	 * ***********************************************************************************
	 */

	/**
	 * 
	 * Takas talebinin hedef kullanıcı tarafından reddedilmesi,
	 * Templates/Views/BookSwap/index Received Request Table içerisinde kullanıldı.
	 * 
	 **/
	/*********** http://localhost:8080/bookswap/rejectswap/id *************/
	@RequestMapping(value = "/rejectswap/{id}", method = RequestMethod.GET)
	public String swapReject(@PathVariable(name = "id") Long id, Principal principal) {
		BookSwap bookSwap = bookSwapService.get(id);
		bookSwap.setTargetCheck(false);
		bookSwapService.save(bookSwap);
		return "redirect:/bookswap";
	}

	/**
	 * 
	 * Takas talebinin hedef kullanıcı tarafından kabul edilmesi durumunda kitap
	 * seçeceği sayfa , swap objesi ve talep gönderen kişinin kitaplarının bulunduğu
	 * sayfadır. Templates/Views/BookSwap/accept
	 **/
	/*********** http://localhost:8080/bookswap/acceptswap/id *************/
	@RequestMapping("/acceptswap/{id}")
	public ModelAndView swapAccept(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/BookSwap/accept");
		BookSwap bookSwap = bookSwapService.get(id);
		List<UsersBook> usersBook = usersBookService.getUsersBookByUserId(bookSwap.getSenderId());
		mav.addObject("bookSwap", bookSwap);
		mav.addObject("usersBook", usersBook);

		return mav;
	}

	/**
	 * 
	 * Takas talebinin hedef kullanıcı tarafından kabul edilmesinin kayıtı,
	 * Templates/Views/BookSwap/index Received Request Table içerisinde kullanıldı.
	 **/
	/*********** http://localhost:8080/bookswap/acceptsave/id/bookId *************/
	@RequestMapping(value = "/acceptsave/{id}/{BookId}", method = RequestMethod.GET)
	public @ResponseBody String swapAcceptSave(@PathVariable(name = "id") Long id,
			@PathVariable(name = "BookId") Long BookId, Principal principal) {
		BookSwap bookSwap = bookSwapService.get(id);
		Users user = userService.getByUserName(principal.getName());
		bookSwap.setTargetCheck(true);
		bookSwap.setSwapStatus(user.getUserEmail() + " tarafından karşı takas gönderilmiştir");
		bookSwapService.save(bookSwap);
		return "bookswap";
	}

	/**
	 * 
	 * Kullanıcının diğer bir kullanıcıdan kitap takasını kayıt etmek için
	 * kullanılan fonksiyon Kullanıcını Id, Hedef Id, Hedef Kitap Id ve mesaj ögesi
	 * içerir.
	 * 
	 **/
	/*********** http://localhost:8080/bookswap/swapcreate/id *************/
	@RequestMapping("/swapcreate/{id}")
	public String swapCreate(@PathVariable(name = "id") Long id, Principal principal) {
		UsersBook usersBook = usersBookService.get(id);
		Users user = userService.getByUserName(principal.getName());
		int UserBookNumber = usersBookService.UserBookNumberByUserId(user.getUserId());

		BookSwap bookSwap = new BookSwap();

		if (UserBookNumber > 0) {

			// Sender
			bookSwap.setSenderId(user.getUserId());

			// Target
			bookSwap.setTargetId(usersBook.getUserId());
			bookSwap.setTargetBookId(usersBook.getUserBookId());

			// Status

			bookSwap.setSwapStatus(
					user.getUserEmail() + "Tarafından " + usersBook.getUserBookName() + " kitabı takas teklif edildi.");
			bookSwapService.save(bookSwap);
		}

		return "redirect:/usersbook";
	}

	/**
	 * Kullanıcının tamamlanmış, gönderilmiş ve gelen taleplerini görmesi için
	 * saglanan sayfadır. swap Servisinden yararlanılarak oluştulan fonksiyon ile
	 * belirlenen talepler bookSwap Listleri olarak getirilir. Bu listeler
	 * mapServisi ile UserSwapDto ya dönüştürülür ve sayfadaki ilgili işlemler için
	 * gönderilir.
	 **/
	@RequestMapping("")
	/*********** http://localhost:8080/bookswap *************/
	public String bookSwapIndex(Model model, Principal principal) {
		Users user = userService.getByUserName(principal.getName());

		List<BookSwap> bookSwapSuccess = bookSwapService.getSuccessSwapByUserId(user.getUserId());
		List<BookSwap> bookSwapRequest = bookSwapService.getRequestSwapByUserId(user.getUserId());
		List<BookSwap> bookSwapReceived = bookSwapService.getReceivedSwapByUserId(user.getUserId());

		List<UserSwapDto> bookSwapSuccessDtos = mapService.convertDTO(bookSwapSuccess);
		List<UserSwapDto> bookSwapRequestDtos = mapService.convertDTO(bookSwapRequest);
		List<UserSwapDto> bookSwapReceivedDtos = mapService.convertDTO(bookSwapReceived);

		model.addAttribute("bookSwapSuccess", bookSwapSuccessDtos);
		model.addAttribute("bookSwapRequest", bookSwapRequestDtos);
		model.addAttribute("bookSwapReceived", bookSwapReceivedDtos);
		return "./Views/BookSwap/index";
	}

}
