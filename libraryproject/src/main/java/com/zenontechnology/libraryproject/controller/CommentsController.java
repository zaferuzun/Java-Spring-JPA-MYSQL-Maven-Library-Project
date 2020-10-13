package com.zenontechnology.libraryproject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zenontechnology.libraryproject.entity.Comments;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.service.CommentsService;

@Controller
@RequestMapping("/comments")
public class CommentsController {

	@Autowired
	private CommentsService commentsService;

	@Autowired
	UserRepository userService;

	@RequestMapping(value = "/bookcommentsave", method = RequestMethod.POST)
	public String commentSaveByBookId(@ModelAttribute("bookComment") Comments bookComment, Principal principal) {

		bookComment.setAdminCheck(false);
		bookComment.setUserId(userService.getUserIdbyUserEmail(principal.getName()));
		commentsService.save(bookComment);
		return "redirect:/books/details/" + bookComment.getBookId();
	}

	@RequestMapping(value = "/authorcommentsave", method = RequestMethod.POST)
	public String commentSaveByAuthorId(@ModelAttribute("authorComment") Comments authorComment, Principal principal) {

		authorComment.setAdminCheck(false);
		authorComment.setUserId(userService.getUserIdbyUserEmail(principal.getName()));
		commentsService.save(authorComment);
		return "redirect:/authors/details/" + authorComment.getAuthorId();
	}

	@RequestMapping(value = "/publishercommentsave", method = RequestMethod.POST)
	public String commentSaveByPublisherId(@ModelAttribute("publicherComment") Comments publicherComment,
			Principal principal) {

		publicherComment.setAdminCheck(false);
		publicherComment.setUserId(userService.getUserIdbyUserEmail(principal.getName()));
		commentsService.save(publicherComment);
		return "redirect:/publishers/details/" + publicherComment.getPublisherId();
	}

	@RequestMapping(value = "/userbookcommentsave", method = RequestMethod.POST)
	public String commentSaveUserBookId(@ModelAttribute("userbookComment") Comments userbookComment,
			Principal principal) {

		userbookComment.setAdminCheck(false);
		userbookComment.setUserId(userService.getUserIdbyUserEmail(principal.getName()));
		commentsService.save(userbookComment);
		return "redirect:/usersbook/details/" + userbookComment.getUserBookId();
	}
}
