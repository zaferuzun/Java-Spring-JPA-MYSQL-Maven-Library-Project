package com.zenontechnology.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
	@Query("SELECT Count(OrderId) FROM Orders b WHERE b.UserId = :UserId and b.BookId = :BookId")
	int CheckUserBook(@Param("UserId") Long UserId, @Param("BookId") Long BookId);

	@Query("SELECT b FROM Orders b WHERE b.UserId = :UserId")
	List<Orders> getOrdersByUserId(@Param("UserId") Long UserId);

	@Query("SELECT b.OrderId FROM Orders b WHERE b.UserId = :UserId and b.BookId = :BookId")
	Long getOrderId(@Param("UserId") Long UserId, @Param("BookId") Long BookId);
}
