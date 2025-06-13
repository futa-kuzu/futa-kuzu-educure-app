package jp.co.hoge.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ExpenseForm {

    @NotBlank(message = "日付を入力してください")
    private String expenseDate;

    @NotBlank(message = "金額を入力してください")
    @Pattern(regexp = "^[0-9]+$", message = "金額は数字で入力してください")
    private String expenseAmount;

    @NotBlank(message = "カテゴリを選択してください")
    private String categoryId;

    // getter/setter
    public String getExpenseDate() {
        return expenseDate;
    }
    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }
    public void setExpenseAmount(String expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public String getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
