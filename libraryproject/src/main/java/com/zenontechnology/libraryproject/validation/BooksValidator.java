/*
 * package com.zenontechnology.libraryproject.validation;
 * 
 * import org.springframework.stereotype.Component; import
 * org.springframework.validation.Errors; import
 * org.springframework.validation.Validator;
 * 
 * import com.zenontechnology.libraryproject.entity.UsersBook;
 * 
 * @Component public class BooksValidator implements Validator {
 * 
 * @Override public boolean supports(Class<?> clazz) { return
 * clazz.equals(UsersBook.class); }
 * 
 * @Override public void validate(Object target, Errors errors) { UsersBook
 * usersbook = (UsersBook) target;
 * 
 * if (usersbook.getUserBookName() == "deneme") {
 * System.out.print(usersbook.getUserBookName() + "d");
 * errors.rejectValue("UserBookName",
 * "error.book.bookCreating.yearOfFoundationError", "deneme olamaz"); }
 * 
 * // For constraint annotations // javax.validation.Validator validator = //
 * Validation.buildDefaultValidatorFactory().getValidator(); } }
 */