package com.zenontechnology.libraryproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zenontechnology.libraryproject.entity.Orders;
import com.zenontechnology.libraryproject.repository.OrdersRepository;

@Service
public class OrdersService {
	@Autowired
	private OrdersRepository ordersRepository;

	public List<Orders> listAll() {
		return ordersRepository.findAll();
	}

	public List<Orders> getOrdersByUserId(Long UserId) {
		return ordersRepository.getOrdersByUserId(UserId);
	}

	public void save(Orders Order) {
		ordersRepository.save(Order);
	}

	public Orders get(Long id) {
		return ordersRepository.findById(id).get();
	}

	public void delete(Long id) {
		ordersRepository.deleteById(id);
	}

	public int CheckUserBook(Long UserId, Long BookId) {
		return ordersRepository.CheckUserBook(UserId, BookId);
	}

	public Long getOrderId(Long UserId, Long BookId) {
		return ordersRepository.getOrderId(UserId, BookId);
	}

}
