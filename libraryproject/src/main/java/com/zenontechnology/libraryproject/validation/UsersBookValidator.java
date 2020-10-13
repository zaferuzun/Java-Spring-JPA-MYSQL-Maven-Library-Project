package com.zenontechnology.libraryproject.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zenontechnology.libraryproject.entity.UsersBook;

public class UsersBookValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UsersBook.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UsersBook usersbook = (UsersBook) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserBookName",
				"error.usersbook.usersBookCreating.NotFoundBookName", "Lütfen Kitap Adı Giriniz");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserBookAuthor",
				"error.usersbook.usersBookCreating.NotFoundAuthorName", "Lütfen Yazar Adı Giriniz");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "UserBookPublisher",
				"error.usersbook.usersBookCreating.NotFoundPublisherName", "Lütfen Yayın Evi Giriniz");

	}
}
