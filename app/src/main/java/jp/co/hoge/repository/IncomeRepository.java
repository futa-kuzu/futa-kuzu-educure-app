package jp.co.hoge.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.hoge.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query("SELECT COALESCE(SUM(i.amount), 0) FROM Income i WHERE i.userId = :userId AND MONTH(i.date) = MONTH(CURRENT_DATE) AND YEAR(i.date) = YEAR(CURRENT_DATE)")
    BigDecimal sumIncomeForLatestMonth(@Param("userId") Long userId);

    @Query("SELECT i FROM Income i WHERE i.userId = :userId AND YEAR(i.date) = :year AND MONTH(i.date) = :month ORDER BY i.date ASC")
    List<Income> findByUserIdAndYearMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);
    
    @Query(value = "SELECT COALESCE(SUM(i.amount), 0) FROM incomes i WHERE i.user_id = :userId AND EXTRACT(YEAR FROM i.date) = :year AND EXTRACT(MONTH FROM i.date) = :month", nativeQuery = true)
    BigDecimal getTotalIncomeByUserIdAndYearMonth(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);


}
