package com.zenontechnology.libraryproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.dto.BookAuthorDto;
import com.zenontechnology.libraryproject.dto.UserSwapDto;
import com.zenontechnology.libraryproject.entity.Authors;
import com.zenontechnology.libraryproject.entity.BookSwap;
import com.zenontechnology.libraryproject.entity.Books;
import com.zenontechnology.libraryproject.repository.BooksRepository;
import com.zenontechnology.libraryproject.repository.UserRepository;
import com.zenontechnology.libraryproject.repository.UsersBookRepository;

@Service
public class MapService {

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository usersRepository;

	@Autowired
	private UsersBookRepository usersBookRepository;

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
	 * Manuel mapping
	 **/
	public List<UserSwapDto> convertDTO(List<BookSwap> bookSwaps) {
		List<UserSwapDto> userSwapDtos = new ArrayList<UserSwapDto>();

		for (BookSwap bookSwap : bookSwaps) {
			UserSwapDto userSwapDto = new UserSwapDto();
			String senderUserEmail = usersRepository.getUserNamebyUserId(bookSwap.getSenderId());
			String targetUserEmail = usersRepository.getUserNamebyUserId(bookSwap.getTargetId());
			String senderBookName = usersBookRepository.getUsersBookNamebyBookId(bookSwap.getSenderBookId());
			String targetBookName = usersBookRepository.getUsersBookNamebyBookId(bookSwap.getTargetBookId());

			userSwapDto.setSwapId(bookSwap.getSwapId());
			userSwapDto.setSenderBookId(bookSwap.getSenderBookId());
			userSwapDto.setSenderBookName(senderBookName);
			userSwapDto.setSenderId(bookSwap.getSenderId());
			userSwapDto.setSenderName(senderUserEmail);
			userSwapDto.setSenderCheck(bookSwap.getSenderCheck());
			userSwapDto.setSwapStatus(bookSwap.getSwapStatus());

			userSwapDto.setTargetBookId(bookSwap.getTargetBookId());
			userSwapDto.setTargetBookName(targetBookName);
			userSwapDto.setTargetCheck(bookSwap.getTargetCheck());
			userSwapDto.setTargetId(bookSwap.getTargetId());
			userSwapDto.setTargetName(targetUserEmail);
			userSwapDto.setLocationMeetway(bookSwap.getLocationMeetway());
			userSwapDtos.add(userSwapDto);
		}
		return userSwapDtos;
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
