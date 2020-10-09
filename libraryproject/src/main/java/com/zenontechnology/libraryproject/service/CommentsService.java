package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Comments;
import com.zenontechnology.libraryproject.repository.CommentsRepository;

@Service
public class CommentsService {
	@Autowired
	private CommentsRepository commentsRepository;

	public List<Comments> getBookCommentsByBookId(Long BookId) {
		return commentsRepository.getBookCommentsByBookId(BookId);
	}

	public List<Comments> getAuthorCommentsByAuthorId(Long BookId) {
		return commentsRepository.getAuthorCommentsByAuthorId(BookId);
	}

	public List<Comments> getPublisherCommentsByPublisherId(Long BookId) {
		return commentsRepository.getPublisherCommentsByPublisherId(BookId);
	}

	public List<Comments> getUserBookCommentsByUserBookId(Long BookId) {
		return commentsRepository.getUserBookCommentsByUserBookId(BookId);
	}

	public void save(Comments comment) {
		commentsRepository.save(comment);
	}

	public List<Comments> listAll() {
		return commentsRepository.findAll();
	}

	public List<Comments> listBooks() {
		return commentsRepository.listBooks();
	}

	public void delete(Long id) {
		commentsRepository.deleteById(id);
	}
}
