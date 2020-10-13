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

import com.zenontechnology.libraryproject.dto.CommentsDto;
import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.function.FileUploadUtil;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.AuthorsService;
import com.zenontechnology.libraryproject.service.BooksService;
import com.zenontechnology.libraryproject.service.CommentsService;
import com.zenontechnology.libraryproject.service.MapService;
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

	@Autowired
	private CommentsService commentsService;

	@Autowired
	private MapService mapService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	/**
	 * Giriş Ekranı
	 */
	/*********** http://localhost:8080/manager *************/
	@RequestMapping("/manager")
	public String managerIndex(Model model) {

		return "./Views/Manager/index";
	}

	/**
	 * BOOK
	 * 
	 * 
	 **/

	/**
	 * Tüm kitapların listelendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/book *************/
	@RequestMapping("/manager/book")
	public String managerBookIndex(Model model) {
		List<Books> listBooks = booksService.listAll();
		model.addAttribute("listBooks", listBooks);
		return "./Views/Manager/bookIndex";
	}

	/**
	 * Tüm kitapların eklendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/bookCreate *************/
	@RequestMapping("/manager/bookCreate")
	public String managerBookCreate(Model model) {
		Books book = new Books();
		model.addAttribute("book", book);

		return "./Views/Manager/bookCreate";
	}

	/**
	 * Kitapların güncellendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/bookEdit *************/
	@RequestMapping("/manager/bookEdit/{id}")
	public ModelAndView managerBookEdit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/bookEdit");
		Books book = booksService.get(id);
		mav.addObject("book", book);
		book.setBookId(id);
		return mav;
	}

	/**
	 * Kitapların eklediği çağrıdır
	 */
	/*********** http://localhost:8080/manager/bookSave *************/
	@RequestMapping(value = "/manager/bookSave", method = RequestMethod.POST)
	public String managerBookSave(@ModelAttribute("book") Books book,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		book.setBookImage(fileName);
		booksService.save(book);

		String uploadDir = "images/books-photos/" + book.getBookId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/manager/book";
	}

	/**
	 * Güncellenmiş kitabın eklediği çağrıdır
	 */
	/*********** http://localhost:8080/manager/bookEditSave *************/
	@RequestMapping(value = "/manager/bookEditSave", method = RequestMethod.POST)
	public String managerBookEditSave(@ModelAttribute("book") Books book,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		if (multipartFile.getSize() != 0) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			book.setBookImage(fileName);
			String uploadDir = "images/books-photos/" + book.getBookId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		booksService.save(book);

		return "redirect:/manager/book";
	}

	/**
	 * Tüm kitapların silindiği çağrıdır
	 */
	/*********** http://localhost:8080/manager/bookDelete/{id} *************/
	@RequestMapping("/manager/bookDelete/{id}")
	public String managerBookDelete(@PathVariable(name = "id") Long id) {
		booksService.delete(id);
		return "redirect:/manager/book";
	}

	/**
	 * Publisher
	 * 
	 * 
	 **/

	/**
	 * Tüm yayın evlerinin listelendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/publisher *************/
	@RequestMapping("/manager/publisher")
	public String managerPublisherIndex(Model model) {
		List<Publishers> listPublishers = publishersService.listAll();
		model.addAttribute("listPublishers", listPublishers);
		return "./Views/Manager/publisherIndex";
	}

	/**
	 * Yayın evlerinin eklendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/publisherCreate *************/
	@RequestMapping("/manager/publisherCreate")
	public String managerPublisherCreate(Model model) {
		Publishers publisher = new Publishers();
		model.addAttribute("publisher", publisher);

		return "./Views/Manager/publisherCreate";
	}

	/**
	 * Yayın evlerinin güncellendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/publisherEdit/{id} *************/
	@RequestMapping("/manager/publisherEdit/{id}")
	public ModelAndView managerPublisherEdit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/publisherEdit");
		Publishers publisher = publishersService.get(id);
		mav.addObject("publisher", publisher);
		publisher.setPublisherId(id);
		return mav;
	}

	/**
	 * Yayın evlerinin eklendiği çağrıdır.
	 */
	/*********** http://localhost:8080/manager/publisherSave *************/
	@RequestMapping(value = "/manager/publisherSave", method = RequestMethod.POST)
	public String managerPublisherSave(@ModelAttribute("publisher") Publishers publisher,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		publisher.setPublisherImage(fileName);
		publishersService.save(publisher);

		String uploadDir = "images/publishers-photos/" + publisher.getPublisherId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/manager/publisher";
	}

	/**
	 * Güncellenen Yayın evlerinin eklendiği çağrıdır.
	 */
	/*********** http://localhost:8080/manager/publisherEditSave *************/
	@RequestMapping(value = "/manager/publisherEditSave", method = RequestMethod.POST)
	public String managerPublisherEditSave(@ModelAttribute("publisher") Publishers publisher,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {

		if (multipartFile.getSize() != 0) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			publisher.setPublisherImage(fileName);
			String uploadDir = "images/publishers-photos/" + publisher.getPublisherId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}

		publishersService.save(publisher);

		return "redirect:/manager/publisher";
	}

	/**
	 * Yayın evlerinin silindiği çağrıdır.
	 */
	/*********** http://localhost:8080/manager/publisherEditSave *************/
	@RequestMapping("/manager/publisherDelete/{id}")
	public String managerPublisherDelete(@PathVariable(name = "id") Long id) {
		publishersService.delete(id);
		return "redirect:/manager/publisher";
	}

	/**
	 * Author
	 * 
	 * 
	 **/

	/**
	 * Tüm yazarların listelendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/author *************/
	@RequestMapping("/manager/author")
	public String managerAuthorIndex(Model model) {
		List<Authors> listAuthors = authorsService.listAll();
		model.addAttribute("listAuthors", listAuthors);
		return "./Views/Manager/authorIndex";
	}

	/**
	 * Yazarların eklendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/authorCreate *************/
	@RequestMapping("/manager/authorCreate")
	public String managerAuthorCreate(Model model) {
		Authors author = new Authors();
		model.addAttribute("author", author);

		return "./Views/Manager/authorCreate";
	}

	/**
	 * Yazarların güncellendiği sayfadır.
	 */
	/*********** http://localhost:8080/manager/authorEdit/{id} *************/
	@RequestMapping("/manager/authorEdit/{id}")
	public ModelAndView managerAuthorEdit(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("./Views/Manager/authorEdit");
		Authors author = authorsService.get(id);
		mav.addObject("author", author);
		author.setAuthorId(id);
		return mav;
	}

	/**
	 * Güncellenen yazarların eklendiği çağrıdır.
	 */
	/*********** http://localhost:8080/manager/authorEditSave *************/
	@RequestMapping(value = "/manager/authorEditSave", method = RequestMethod.POST)
	public String managerAuthorEditSave(@ModelAttribute("author") Authors author,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		if (multipartFile.getSize() != 0) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			author.setAuthorImage(fileName);
			String uploadDir = "images/authors-photos/" + author.getAuthorId();

			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		}
		authorsService.save(author);

		return "redirect:/manager/author";
	}

	/**
	 * Yazarların kaydedildiği çağrıdır.
	 */
	/*********** http://localhost:8080/manager/authorSave *************/
	@RequestMapping(value = "/manager/authorSave", method = RequestMethod.POST)
	public String managerAuthorSave(@ModelAttribute("author") Authors author,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		author.setAuthorImage(fileName);
		authorsService.save(author);

		String uploadDir = "images/authors-photos/" + author.getAuthorId();

		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		return "redirect:/manager/author";
	}

	/**
	 * Yazarların silindiği çağrıdır
	 */
	/*********** http://localhost:8080/manager/authorDelete/{id} *************/
	@RequestMapping("/manager/authorDelete/{id}")
	public String managerAuthorDelete(@PathVariable(name = "id") Long id) {
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

	/**
	 * 
	 * Comment
	 * 
	 */
	@RequestMapping("/manager/comment")
	public String allComment(Model model) {
		List<CommentsDto> bookComments = mapService.getAllComment();

		model.addAttribute("bookComments", bookComments);
		return "./Views/Manager/commentIndex";
	}

	@RequestMapping("/manager/commentcontrol")
	public String commentControl(Model model) {
		List<CommentsDto> allComments = mapService.getAllComment();

		model.addAttribute("allComments", allComments);
		return "./Views/Manager/commentIndex";
	}

	@RequestMapping("/manager/commentdelete/{id}")
	public String commentDelete(@PathVariable(name = "id") Long id) {
		commentsService.delete(id);
		return "redirect:/manager/commentcontrol";
	}
}
