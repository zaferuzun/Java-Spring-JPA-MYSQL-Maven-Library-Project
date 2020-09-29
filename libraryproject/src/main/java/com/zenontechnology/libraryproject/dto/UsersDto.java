package com.zenontechnology.libraryproject.dto;

import java.time.LocalDate;

public class UsersDto {

	private Long UserId;

	private String UserName;

	private String UserSurname;

	private String UserEmail;

	private LocalDate UserRegisterDate;

	private Boolean UserPermission;

	private String UserPassword;

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserSurname() {
		return UserSurname;
	}

	public void setUserSurname(String userSurname) {
		UserSurname = userSurname;
	}

	public String getUserEmail() {
		return UserEmail;
	}

	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}

	public LocalDate getUserRegisterDate() {
		return UserRegisterDate;
	}

	public void setUserRegisterDate(LocalDate userRegisterDate) {
		UserRegisterDate = userRegisterDate;
	}

	public Boolean getUserPermission() {
		return UserPermission;
	}

	public void setUserPermission(Boolean userPermission) {
		UserPermission = userPermission;
	}

	public String getUserPassword() {
		return UserPassword;
	}

	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}

}
