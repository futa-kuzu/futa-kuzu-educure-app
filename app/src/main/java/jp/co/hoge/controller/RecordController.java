package jp.co.hoge.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.entity.Category;
import jp.co.hoge.entity.Expense;
import jp.co.hoge.entity.Income;
import jp.co.hoge.repository.CategoryRepository;
import jp.co.hoge.repository.ExpenseRepository;
import jp.co.hoge.service.IncomeExpenseService;

@Controller
public class RecordController {

    @Autowired
    private IncomeExpenseService incomeExpenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @GetMapping("/record")
    public String viewData(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "tab", required = false, defaultValue = "income") String tab,
            HttpSession session, Model model) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "redirect:/login";
        }

        LocalDate now = LocalDate.now();
        if (year == null || month == null) {
            year = now.getYear();
            month = now.getMonthValue();
        }

        // 現在の月のLocalDate
        LocalDate currentMonth = LocalDate.of(year, month, 1);
        LocalDate prevMonth = currentMonth.minusMonths(1);
        LocalDate nextMonth = currentMonth.plusMonths(1);

        // 収入・支出データ取得
        BigDecimal totalIncome = incomeExpenseService.getTotalIncome(userId, year, month);
        List<Income> incomeList = incomeExpenseService.getMonthlyIncomes(userId, year, month);
        List<Expense> expenseList = incomeExpenseService.getMonthlyExpenses(userId, year, month);

        Map<Integer, String> categoryMap = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(Category::getCategoryId, Category::getCategoryName));

        List<Map<String, Object>> incomes = incomeList.stream().map(income -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", income.getDate().format(dateFormatter));
            map.put("amount", income.getAmount());
            map.put("incomeId", income.getIncomeId());
            return map;
        }).collect(Collectors.toList());

        List<Map<String, Object>> expenses = expenseList.stream().map(expense -> {
            Map<String, Object> map = new HashMap<>();
            map.put("date", expense.getDate().format(dateFormatter));
            map.put("amount", expense.getAmount());
            map.put("expenseId", expense.getExpenseId());
            map.put("categoryName", categoryMap.get(expense.getCategoryId()));
            return map;
        }).collect(Collectors.toList());

        Map<String, BigDecimal> categoryTotals = new HashMap<>();
        for (Expense expense : expenseList) {
            String categoryName = categoryMap.get(expense.getCategoryId());
            categoryTotals.put(categoryName,
                categoryTotals.getOrDefault(categoryName, BigDecimal.ZERO).add(expense.getAmount()));
        }

        // JSPに渡す
        model.addAttribute("incomes", incomes);
        model.addAttribute("expenses", expenses);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("categoryTotals", categoryTotals);

        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("prevYear", prevMonth.getYear());
        model.addAttribute("prevMonth", prevMonth.getMonthValue());
        model.addAttribute("nextYear", nextMonth.getYear());
        model.addAttribute("nextMonth", nextMonth.getMonthValue());
        model.addAttribute("tab", tab);

        return "records";
    }

}
