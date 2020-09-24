package com.zenontechnology.libraryproject.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long UserId;

	@Column(name = "Username")
	private String UserName;

	@Column(name = "UserSurname")
	private String UserSurname;

	@Column(name = "UserEmail")
	private String UserEmail;

	@Column(name = "UserRegisterDate")
	private Date UserRegisterDate;

	@Column(name = "UserPermission")
	private Boolean UserPermission;

	@Column(name = "UserPassword")
	private String UserPassword;

	protected Users() {

	}

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
