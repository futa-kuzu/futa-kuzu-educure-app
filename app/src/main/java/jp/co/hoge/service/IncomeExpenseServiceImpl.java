package jp.co.hoge.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.entity.Expense;
import jp.co.hoge.entity.Income;
import jp.co.hoge.repository.ExpenseRepository;
import jp.co.hoge.repository.IncomeRepository;

@Service
public class IncomeExpenseServiceImpl implements IncomeExpenseService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public BigDecimal getTotalIncome(Long userId, int year, int month) {
        return incomeRepository.getTotalIncomeByUserIdAndYearMonth(userId, year, month);
    }

    @Override
    public BigDecimal getTotalExpense(Long userId, int year, int month) {
        return expenseRepository.getTotalExpenseByUserIdAndYearMonth(userId, year, month);
    }

    @Override
    public List<Income> getMonthlyIncomes(Long userId, int year, int month) {
        return incomeRepository.findByUserIdAndYearMonth(userId, year, month);
    }

    @Override
    public List<Expense> getMonthlyExpenses(Long userId, int year, int month) {
        return expenseRepository.findByUserIdAndYearMonth(userId, year, month);
    }
}

