package com.zenontechnology.libraryproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zenontechnology.libraryproject.dto.BookAuthorDto;
import com.zenontechnology.libraryproject.dto.CommentsDto;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Comments;
import com.zenontechnology.libraryproject.service.BooksService;
import com.zenontechnology.libraryproject.service.CommentsService;
import com.zenontechnology.libraryproject.service.MapService;

@Controller
public class BooksController {

	@Autowired
	private BooksService booksService;

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private MapService mapService;

	/**
	 * Kitap işlemleri, Projenin gidişatına göre kitap işlemleri bölümü aslında
	 * online bir kütüphane olarak degiştirilmiştir. Burada ki kitaplar bir
	 * kütüphanenin kitapları olarak düşünülebilir. Kitap sekmesi için kullanıcılar
	 * arasında bir ayrım vardır, USER----> Kitabın temel gösterimi,alma ve iade
	 * işlemlerini yapabilmektedir. EDITOR ---> USER in yaptıklarına ek olarak
	 * Editor panelinden düzeltmelerde bulunabilir. ADMIN ----> Admin Panelinden Tüm
	 * işlemleri yapabilmektedir.
	 */
	private int bookShow = 3;

	/**
	 * Temel olarak kitapların gösterimidir. mapServis içerisinde Model Mapping
	 * kullanılarak Dto oluşturulur ve liste sayfaya gönderilir.
	 */
	/*********** http://localhost:8080/books *************/
	@RequestMapping("/books")
	public String bookIndex(Model model) {
		// List<Books> listBooks = booksService.listAll();
		/*
		 * Object[] denemeList = booksService.listBooks().toArray();
		 * 
		 * for (Object object : denemeList) { System.out.print(object.toString() +
		 * "\n"); }
		 */
		/*
		 * List<BookAuthorDto> usersLocation = mapService.getAllUsersLocation(); for
		 * (BookAuthorDto bookAuthorDto : usersLocation) {
		 * 
		 * System.out.print(bookAuthorDto.getBookName() + "\n"); }
		 */
		/*
		 * List<Books> books = booksService.listByPublisherId();
		 * 
		 * for (Books books2 : books) {
		 * System.out.print(books2.getAuthors().getAuthorName() + "\n"); }
		 */

		List<BookAuthorDto> bookAuthorDto = mapService.getAllUsersLocation2();

		model.addAttribute("listBooks", bookAuthorDto);
		return "./Views/Books/index";
	}

	/**
	 * Index üzerinden seçilen kitabın detaylarını görmesidir. Kitap detayı ile
	 * birlikte belirlenen sayı kadar random kitap detaylar sayfasında yer alır.
	 * 
	 */
	/*********** http://localhost:8080/details/id *************/

	@RequestMapping("/books/details/{id}")
	public String bookDetails(@PathVariable(name = "id") Long id, Model model) {
		Books book = booksService.get(id);

		int allBookNumber = booksService.AllBookNumbers();
		List<Books> listBooks = null;
		if (bookShow > allBookNumber && allBookNumber != 0) {
			listBooks = booksService.listRandomBooks(allBookNumber);
		} else {
			listBooks = booksService.listRandomBooks(bookShow);
		}

		List<CommentsDto> bookComments = mapService.getCommentbyBookId(id);
		Comments bookComment = new Comments();
		bookComment.setBookId(id);
		model.addAttribute("bookComment", bookComment);
		model.addAttribute("bookComments", bookComments);
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("book", book);
		return "./Views/Books/details";
	}

	/*
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
		 */

	/**//*
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
