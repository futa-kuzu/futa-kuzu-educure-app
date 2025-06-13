package jp.co.hoge.DTO;

import java.math.BigDecimal;


public class MenuInfoDto {

    private BigDecimal incomeTotal;      
    private BigDecimal expenseTotal;     
    private BigDecimal balance;         
    private BigDecimal goalAmount;       
    private BigDecimal remainingToGoal;  
    private BigDecimal achievementRate; 

    // コンストラクタ
    public MenuInfoDto(BigDecimal incomeTotal, BigDecimal expenseTotal, BigDecimal balance, BigDecimal goalAmount, BigDecimal remainingToGoal, BigDecimal achievementRate) {
        this.incomeTotal = incomeTotal;
        this.expenseTotal = expenseTotal;
        this.balance = balance;
        this.goalAmount = goalAmount;
        this.remainingToGoal = remainingToGoal;
        this.achievementRate = achievementRate;
    }

    public BigDecimal getIncomeTotal() {
        return incomeTotal;
    }
    public void setIncomeTotal(BigDecimal incomeTotal) {
        this.incomeTotal = incomeTotal;
    }
    
    public BigDecimal getExpenseTotal() {
        return expenseTotal;
    }
    public void setExpenseTotal(BigDecimal expenseTotal) {
        this.expenseTotal = expenseTotal;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getGoalAmount() {
        return goalAmount;
    }
    public void setGoalAmount(BigDecimal goalAmount) {
        this.goalAmount = goalAmount;
    }

    public BigDecimal getRemainingToGoal() {
        return remainingToGoal;
    }
    public void setRemainingToGoal(BigDecimal remainingToGoal) {
        this.remainingToGoal = remainingToGoal;
    }

    public BigDecimal getAchievementRate() {
        return achievementRate;
    }
    public void setAchievementRate(BigDecimal achievementRate) {
        this.achievementRate = achievementRate;
    }
}


