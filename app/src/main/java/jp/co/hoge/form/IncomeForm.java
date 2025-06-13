package jp.co.hoge.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class IncomeForm {

    @NotBlank(message = "日付を入力してください")
    private String incomeDate;

    @NotBlank(message = "金額を入力してください")
    @Pattern(regexp = "^[0-9]+$", message = "金額は数字で入力してください")
    private String incomeAmount;

    // getter/setter
    public String getIncomeDate() {
        return incomeDate;
    }
    public void setIncomeDate(String incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }
    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
}
