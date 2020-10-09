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

	public void save(Comments comment) {
		commentsRepository.save(comment);
	}
}
