package jp.co.hoge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.entity.Expense;
import jp.co.hoge.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	@Override
	public Expense findById(Long id) {
		return expenseRepository.findById(id).orElse(null);
	}
	
	@Override
	public void update(Expense expense) {
		expenseRepository.save(expense);
	}
}
