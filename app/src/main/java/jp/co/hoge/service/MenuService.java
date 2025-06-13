package jp.co.hoge.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.hoge.DTO.MenuInfoDto;
import jp.co.hoge.entity.Goal;
import jp.co.hoge.repository.ExpenseRepository;
import jp.co.hoge.repository.GoalRepository;
import jp.co.hoge.repository.IncomeRepository;

@Service
public class MenuService {

    @Autowired
    private IncomeRepository incomeRepository;
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private GoalRepository goalRepository;

    public MenuInfoDto getMenuInfo(Long userId) {

        // 最新月の収入を取得
        BigDecimal incomeTotal = incomeRepository.sumIncomeForLatestMonth(userId);

        // 最新月の支出を取得
        BigDecimal expenseTotal = expenseRepository.sumExpenseForLatestMonth(userId);

        // 現在の年月（yyyyMM）を取得
        String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

        // 目標金額をgoalsテーブルから取得
        Optional<Goal> goalOpt = goalRepository.findByUserId(userId);
        BigDecimal goalAmount = goalOpt.map(Goal::getAmount).orElse(BigDecimal.ZERO);

        // 収支
        BigDecimal balance = incomeTotal.subtract(expenseTotal);

        // 目標まで残り金額
        BigDecimal remainingToGoal = goalAmount.subtract(balance);

        // 達成率の計算（収支 / 目標金額 * 100）
        BigDecimal achievementRate = BigDecimal.ZERO;
        if (goalAmount.compareTo(BigDecimal.ZERO) > 0) {
            achievementRate = balance.divide(goalAmount, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        }

        // 整数部分のみで返す
        return new MenuInfoDto(
            incomeTotal.setScale(0, RoundingMode.DOWN), 
            expenseTotal.setScale(0, RoundingMode.DOWN), 
            balance.setScale(0, RoundingMode.DOWN), 
            goalAmount.setScale(0, RoundingMode.DOWN), 
            remainingToGoal.setScale(0, RoundingMode.DOWN), 
            achievementRate.setScale(0, RoundingMode.DOWN)
        );
    }
}
