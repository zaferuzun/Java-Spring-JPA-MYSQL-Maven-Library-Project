package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.repository.AuthorsRepository;

@Service
public class AuthorsService {

	@Autowired
	private AuthorsRepository authorsRepository;

	public List<Authors> listAll() {
		return authorsRepository.findAll();
	}

	public void save(Authors author) {
		authorsRepository.save(author);
	}

	public Authors get(Long id) {
		return authorsRepository.findById(id).get();
	}

	public void delete(Long id) {
		authorsRepository.deleteById(id);
	}

}
