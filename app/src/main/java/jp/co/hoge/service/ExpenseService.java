package jp.co.hoge.service;

import jp.co.hoge.entity.Expense;

public interface ExpenseService {

	Expense findById(Long id);
	void update(Expense expense);
}
