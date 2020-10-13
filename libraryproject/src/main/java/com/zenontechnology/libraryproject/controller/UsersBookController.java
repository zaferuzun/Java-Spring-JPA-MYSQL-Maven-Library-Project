package com.zenontechnology.libraryproject.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zenontechnology.libraryproject.dto.CommentsDto;
import com.zenontechnology.libraryproject.entity.Comments;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.entity.UsersBook;
import com.zenontechnology.libraryproject.function.FileUploadUtil;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.MapService;
import com.zenontechnology.libraryproject.service.UsersBookService;
import com.zenontechnology.libraryproject.validation.UsersBookValidator;

@Controller
@RequestMapping("/usersbook")
public class UsersBookController {

	@Autowired
	private UsersBookService usersBookService;

	@Autowired
	UserRepository userService;

	@Autowired
	private MapService mapService;

	private final UsersBookValidator usersBookValidator = new UsersBookValidator();

	@InitBinder("usersbook")
	void setUsersBookValidator(WebDataBinder binder) {
		binder.addValidators(usersBookValidator);
	}

	/*************************************************************************************
	 * Kullanıcı kitapları, Üye olup giriş yapan kullanıcılar için oluşturulmuştur.
	 * Burada ki kitaplar kütüphanede olan kitaplardan bağımsızdır. Kitapları
	 * kullanıcılar kendileri ekler. Yayın evi ve yazar gibi değişkenleri kendileri
	 * belirtir. Burada ki kitaplar takaslanmak için bulunur ve kullanıcılar bu
	 * kitapları birbiriyle takaslayabilmektedir.
	 * 
	 ************************************************************************************
	 * Normal kütüphanede olan kitapların yazarları ve yayın evleri hakkında
	 * bilgiler admin tarafından veritabanından eklenmektedir. Burada ki yazar,
	 * yayın evleri kullanıcı tarafından eklendiği için veritabanına
	 * kaydedilmemektedir. Yani kullanıcı kitapları veritabanında bir yazar Id veya
	 * yayın evi Id sine sahip degiller sadece isim olarak kaydedilmektedir.
	 * 
	 * *********************************************************************************
	 * 
	 * Bu durumlar ADMIN, USER, EDITOR için geçerlidir. Lakin Admin ve Editor
	 * panellerinden bu işlemlerin editlenmesi gibi işlemler bulunmamaktadır.
	 * 
	 * ***********************************************************************************
	 */

	/**
	 * Temel olarak kullanıcıların eklediği kitapların gösterimidir.
	 */
	/*********** http://localhost:8080/usersbook *************/
	@RequestMapping("")
	public String usersBookIndex(Model model) {
		List<UsersBook> listUserBooks = usersBookService.listAll();
		model.addAttribute("listUserBooks", listUserBooks);
		return "./Views/UsersBook/index";
	}

	/**
	 * Kullanıcıların kitaplarını silmesi.
	 */
	/*********** http://localhost:8080/usersbook/delete/{id} *************/
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		usersBookService.delete(id);
		return "redirect:/usersbook";
	}

	/**
	 * Kullanıcıların kitap oluşturması için kullanılan sayfa.
	 */
	/*********** http://localhost:8080/usersbook/create *************/
	@GetMapping("create")
	public String showNewUserForm(Model model) {
		UsersBook usersbook = new UsersBook();
		model.addAttribute("usersbook", usersbook);

		return "./Views/UsersBook/create";
	}

	/**
	 * Kullanıcıların kitap oluşturulan kitabın veritabanına kaydedilmesi Create
	 * üzerinden gönderilen post istegi karşılanıyor. Validation işlemleri zaten
	 * yapılmış olarak geliyor. Dosya boyutu ise controller tarafından
	 * sağlanır.Herhangi bir hata ile karşılaşılmadığı taktirde giriş yapmış
	 * kullanıcı email üzerinden userRepository kullanılarak kullanıcı id
	 * belirlenir. Kullanıcının üzerine kitap eklenir. Daha sonra dosya belirtilen
	 * yola (path) kaydedilir.Kayıtın başarılı olduğu message degişkeni ile
	 * /usersbook/create yoluna gönderilir ve js ile message içerigi kontrol edilip
	 * sweet alert kullanımı sağlanır.
	 * 
	 * **************************************************************************
	 * Eklenecek: Kullanıcının kitap ekleme sayısı admin tarafından default olarak
	 * belirlenecek ve kullanıcı bu kontrolden geçirilecek.
	 */
	/*********** http://localhost:8080/usersbook/save *************/

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String usersBookSave(@Valid @ModelAttribute("usersbook") UsersBook usersbook,
			final BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		usersbook.setUserBookImage(fileName);

		if (multipartFile.getSize() == 0) {
			bindingResult.rejectValue("UserBookImage", "error.usersbook.usersBookCreating.FileError",
					"Lütfen Yayın Evi Giriniz");
		}

		if (bindingResult.hasErrors()) {
			return "./Views/UsersBook/create";
		}

		Users user = userService.getByUserName(principal.getName());
		usersbook.setUserId(user.getUserId());
		usersBookService.save(usersbook);
		String uploadDir = "images/usersbook-photos/" + usersbook.getUserBookId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		redirectAttributes.addFlashAttribute("message", "Success");
		return "redirect:/usersbook/create";
		/*
		 * if (true) { redirectAttributes.addFlashAttribute("message", "Success");
		 * return "redirect:/usersbook/create";
		 * 
		 * } else { return "redirect:/usersbook"; }
		 */

	}

	/******
	 * Save ile aynı işlemlere sahip lakin gelecek olan dosya boş olabilir. Edit
	 * kısmında dosyayı degiştirilme durumu zorunlu olmadığı için kontrol sağlanır
	 * ve ona göre kayıt işlemi yapılır.
	 *
	 * /*********** http://localhost:8080/usersbook/editsave
	 *************/
	@RequestMapping(value = "/editsave", method = RequestMethod.POST)
	public String usersBookEdit(@Valid @ModelAttribute("usersbook") UsersBook usersbook,
			final BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		if (bindingResult.hasErrors()) {
			return "./Views/UsersBook/create";
		}

		if (multipartFile.getSize() != 0) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			usersbook.setUserBookImage(fileName);
			String uploadDir = "images/usersbook-photos/" + usersbook.getUserBookId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		}
		Users user = userService.getByUserName(principal.getName());
		usersbook.setUserId(user.getUserId());
		usersBookService.save(usersbook);

		redirectAttributes.addFlashAttribute("message", "Success");

		return "redirect:/usersbook/details/" + usersbook.getUserBookId();

	}

	/**
	 * Kullanıcıların kitapların detaylarını gördüğü sayfa
	 */
	/*********** http://localhost:8080/usersbook/details/{id} *************/
	@RequestMapping("/details/{id}")
	public String userBooksDetailsPage(@PathVariable(name = "id") Long id, Model model, Principal principal) {
		UsersBook usersbook = usersBookService.get(id);

		List<CommentsDto> userbookComments = mapService.getCommentbyUserBookId(id);
		Comments userbookComment = new Comments();
		userbookComment.setUserBookId(id);
		model.addAttribute("userbookComment", userbookComment);
		model.addAttribute("userbookComments", userbookComments);
		model.addAttribute("user", userService.getUserNamebyUserId(usersbook.getUserId()));

		model.addAttribute("usersbook", usersbook);
		return "./Views/UsersBook/details";
	}

	/**
	 * Kullanıcıların kitaplarını düzenlediği sayfa
	 */
	/*********** http://localhost:8080/usersbook/edit/{id} *************/
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/UsersBook/edit");
		UsersBook usersbook = usersBookService.get(id);
		usersbook.setUserBookId(id);

		mav.addObject("usersbook", usersbook);

		return mav;
	}

	/**
	 * Kullanıcının details sayfasında baktığı kitabın kendisine ait olup olmadığını
	 * kontrol ediyor.
	 */
	/*********** http://localhost:8080/usersbook/userCheckBook *************/
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

	/**
	 * Kullanıcının kendi eklediği kitapları gördüğü sayfadır.
	 */
	/*********** http://localhost:8080/usersbook/mybook *************/
	@RequestMapping("/mybook")
	public String usersMyBook(Model model, Principal principal) {
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
