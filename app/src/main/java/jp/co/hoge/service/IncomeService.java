package jp.co.hoge.service;

import jp.co.hoge.entity.Income;

public interface IncomeService{
	Income findById(Long id);
	void update(Income income);
}
