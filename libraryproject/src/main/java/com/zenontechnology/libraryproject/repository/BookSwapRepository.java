package com.zenontechnology.libraryproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zenontechnology.libraryproject.entity.BookSwap;

public interface BookSwapRepository extends JpaRepository<BookSwap, Long> {

	@Query("SELECT b FROM BookSwap b WHERE (b.SenderId = :UserId or b.TargetId = :UserId) and (SenderCheck=true and TargetCheck= true)")
	List<BookSwap> getSuccessSwapByUserId(@Param("UserId") Long UserId);

	@Query("SELECT b FROM BookSwap b WHERE b.SenderId = :UserId  and (SenderCheck=null and TargetCheck= null)")
	List<BookSwap> getRequestSwapByUserId(@Param("UserId") Long UserId);

	@Query("SELECT b FROM BookSwap b WHERE  b.TargetId = :UserId and (SenderCheck=null and TargetCheck= null)")
	List<BookSwap> getReceivedSwapByUserId(@Param("UserId") Long UserId);
}
