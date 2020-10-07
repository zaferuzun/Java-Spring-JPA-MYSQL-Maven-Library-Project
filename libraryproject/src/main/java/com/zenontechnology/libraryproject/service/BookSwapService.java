package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.BookSwap;
import com.zenontechnology.libraryproject.repository.BookSwapRepository;

@Service
public class BookSwapService {

	@Autowired
	private BookSwapRepository bookSwapRepository;

	public List<BookSwap> listAll() {
		return bookSwapRepository.findAll();
	}

	public void save(BookSwap bookSwap) {
		bookSwapRepository.save(bookSwap);
	}

	public BookSwap get(Long id) {
		return bookSwapRepository.findById(id).get();
	}

	public void delete(Long id) {
		bookSwapRepository.deleteById(id);
	}

	public List<BookSwap> getSuccessSwapByUserId(Long UserId) {

		return bookSwapRepository.getSuccessSwapByUserId(UserId);

	}

	public List<BookSwap> getRequestSwapByUserId(Long UserId) {
		return bookSwapRepository.getRequestSwapByUserId(UserId);

	}

	public List<BookSwap> getReceivedSwapByUserId(Long UserId) {
		return bookSwapRepository.getReceivedSwapByUserId(UserId);
	}
}
