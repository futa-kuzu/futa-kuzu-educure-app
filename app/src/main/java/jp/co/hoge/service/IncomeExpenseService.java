package jp.co.hoge.service;

import java.math.BigDecimal;
import java.util.List;

import jp.co.hoge.entity.Expense;
import jp.co.hoge.entity.Income;

public interface IncomeExpenseService {
    BigDecimal getTotalIncome(Long userId, int year, int month);
    BigDecimal getTotalExpense(Long userId, int year, int month);
    List<Income> getMonthlyIncomes(Long userId, int year, int month);
    List<Expense> getMonthlyExpenses(Long userId, int year, int month);
}

