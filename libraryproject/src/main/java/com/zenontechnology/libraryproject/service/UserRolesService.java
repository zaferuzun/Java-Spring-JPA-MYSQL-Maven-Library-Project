package com.zenontechnology.libraryproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.UserRoles;
import com.zenontechnology.libraryproject.repository.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;

	public void save(UserRoles userRoles) {
		userRolesRepository.save(userRoles);
	}

}
