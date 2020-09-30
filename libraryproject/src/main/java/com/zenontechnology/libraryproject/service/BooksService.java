package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.repository.BooksRepository;

@Service
public class BooksService {

	@Autowired
	private BooksRepository booksRepository;

	public List<Books> listAll() {
		return booksRepository.findAll();
	}

	public void save(Books book) {
		booksRepository.save(book);
	}

	/**/
	public int findAllActiveUsers() {
		return booksRepository.findAllActiveUsers();
	}

	public int AuthorBookNumber(Long AuthorId) {
		return booksRepository.AuthorBookNumber(AuthorId);
	}

	public int PublisherBookNumber(Long PublisherId) {
		return booksRepository.PublisherBookNumber(PublisherId);
	}

	public int AllBookNumbers() {
		return booksRepository.AllBookNumbers();
	}

	public List<Books> listRandomBooks(int limitNumber) {
		return booksRepository.listRandomBooks(limitNumber);
	}

	/**/
	public Books get(Long id) {
		return booksRepository.findById(id).get();
	}

	public void delete(Long id) {
		booksRepository.deleteById(id);
	}
}
