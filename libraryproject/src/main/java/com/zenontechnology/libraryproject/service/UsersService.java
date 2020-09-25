package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	public List<Users> listAll() {
		return usersRepository.findAll();
	}

	public void save(Users user) {
		usersRepository.save(user);
	}

	public Users get(Long id) {
		return usersRepository.findById(id).get();
	}

	public void delete(Long id) {
		usersRepository.deleteById(id);
	}

}
