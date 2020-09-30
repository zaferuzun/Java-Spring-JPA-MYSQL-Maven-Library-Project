package com.zenontechnology.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zenontechnology.libraryproject.entity.Users;
import com.zenontechnology.libraryproject.repository.LibraryUserDetails;
import com.zenontechnology.libraryproject.repository.UserRepository;

public class LibraryUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
		Users user = userRepository.getByUserName(UserEmail);

		if (user == null) {
			throw new UsernameNotFoundException("User bulunamadı");
		}

		return new LibraryUserDetails(user);
	}

	public void save(Users user) {
		userRepository.save(user);
	}

	public Users getUserByEmail(String UserEmail) {
		Users user = userRepository.getByUserName(UserEmail);
		return user;
	}

}
