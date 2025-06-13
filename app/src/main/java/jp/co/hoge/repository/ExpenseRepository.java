package jp.co.hoge.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.hoge.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e WHERE e.userId = :userId AND MONTH(e.date) = MONTH(CURRENT_DATE) AND YEAR(e.date) = YEAR(CURRENT_DATE)")
    BigDecimal sumExpenseForLatestMonth(@Param("userId") Long userId);

    @Query("SELECT e FROM Expense e WHERE e.userId = :userId AND YEAR(e.date) = :year AND MONTH(e.date) = :month ORDER BY e.date ASC")
    List<Expense> findByUserIdAndYearMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query(value = "SELECT COALESCE(SUM(e.amount), 0) FROM expenses e WHERE e.user_id = :userId AND EXTRACT(YEAR FROM e.date) = :year AND EXTRACT(MONTH FROM e.date) = :month", nativeQuery = true)
    BigDecimal getTotalExpenseByUserIdAndYearMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query(value = "SELECT category_name FROM categories WHERE category_id = :categoryId", nativeQuery = true)
    String findCategoryNameById(@Param("categoryId") Integer categoryId);

}
