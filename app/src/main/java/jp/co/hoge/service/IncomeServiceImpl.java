package jp.co.hoge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.entity.Income;
import jp.co.hoge.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;
	
	@Override
	public Income findById(Long id) {
		return incomeRepository.findById(id).orElse(null);
	}
	
	@Override
	public void update(Income income) {
		incomeRepository.save(income);
	}
}
