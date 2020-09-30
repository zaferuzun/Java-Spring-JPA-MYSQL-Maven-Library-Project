package com.zenontechnology.libraryproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private Long OrderId;

	@Column(name = "BookId")
	private Long BookId;

	@Column(name = "UserId")
	private Long UserId;

	public Orders() {

	}

	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public Long getBookId() {
		return BookId;
	}

	public void setBookId(Long bookId) {
		BookId = bookId;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

}
