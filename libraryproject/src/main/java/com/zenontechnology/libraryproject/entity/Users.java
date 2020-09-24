package com.zenontechnology.libraryproject.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {

	private Long UserId;
	private String UserName;
	private String UserSurname;
	private String UserEmail;
	private Date UserRegisterDate;
	private Boolean UserPermission;
	private String UserPassword;

	protected Users() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Date getUserRegisterDate() {
		return UserRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
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
