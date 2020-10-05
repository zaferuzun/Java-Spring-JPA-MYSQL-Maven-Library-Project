package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.UsersBook;
import com.zenontechnology.libraryproject.repository.UsersBookRepository;

@Service
public class UsersBookService {

	@Autowired
	private UsersBookRepository usersBookRepository;

	public List<UsersBook> listAll() {
		return usersBookRepository.findAll();
	}

	public void save(UsersBook usersBook) {
		usersBookRepository.save(usersBook);
	}

	public UsersBook get(Long id) {
		return usersBookRepository.findById(id).get();
	}

	public void delete(Long id) {
		usersBookRepository.deleteById(id);
	}

	public int CheckHasUserBook(Long UserId, Long UserBookId) {

		return usersBookRepository.CheckHasUserBook(UserId, UserBookId);

	}

	public List<UsersBook> getUsersBookByUserId(Long UserId) {

		return usersBookRepository.getUsersBookByUserId(UserId);

	}

	public int UserBookNumberByUserId(Long UserId) {

		return usersBookRepository.UserBookNumberByUserId(UserId);

	}
}
