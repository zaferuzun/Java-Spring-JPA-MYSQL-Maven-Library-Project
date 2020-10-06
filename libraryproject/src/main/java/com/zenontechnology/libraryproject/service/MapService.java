package com.zenontechnology.libraryproject.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.dto.BookAuthorDto;
import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.repository.BooksRepository;

@Service
public class MapService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * 
	 * Manuel mapping
	 **/
	public List<BookAuthorDto> getAllUsersLocation() {
		return ((List<Books>) booksRepository.findAll()).stream().map(this::convertToUserLocationDTO)
				.collect(Collectors.toList());
	}

	private BookAuthorDto convertToUserLocationDTO(Books book) {
		BookAuthorDto bookAuthorDto = new BookAuthorDto();
		bookAuthorDto.setBookId(book.getAuthorId());
		bookAuthorDto.setAuthorId(book.getBookId());
		bookAuthorDto.setBookName(book.getBookName());
		Authors author = book.getAuthors();
		bookAuthorDto.setAuthorName(author.getAuthorName());

		return bookAuthorDto;
	}

	/**
	 * 
	 * ModelMapper with mapping
	 **/
	public List<BookAuthorDto> getAllUsersLocation2() {
		return ((List<Books>) booksRepository.findAll()).stream().map(this::convertToUserLocationDTO2)
				.collect(Collectors.toList());
	}

	private BookAuthorDto convertToUserLocationDTO2(Books book) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		BookAuthorDto bookAuthorDto = modelMapper.map(book, BookAuthorDto.class);
		return bookAuthorDto;
	}
}
