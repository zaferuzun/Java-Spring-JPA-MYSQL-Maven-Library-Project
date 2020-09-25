package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Publishers;
import com.zenontechnology.libraryproject.repository.PublishersRepository;

@Service
public class PublisherService {

	@Autowired
	private PublishersRepository publishersRepository;

	public List<Publishers> listAll() {
		return publishersRepository.findAll();
	}

	public void save(Publishers publisher) {
		publishersRepository.save(publisher);
	}

	public Publishers get(Long id) {
		return publishersRepository.findById(id).get();
	}

	public void delete(Long id) {
		publishersRepository.deleteById(id);
	}
}
