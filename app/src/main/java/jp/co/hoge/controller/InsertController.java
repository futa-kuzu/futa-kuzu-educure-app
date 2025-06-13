package jp.co.hoge.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.entity.Expense;
import jp.co.hoge.entity.Income;
import jp.co.hoge.entity.User;
import jp.co.hoge.repository.CategoryRepository;
import jp.co.hoge.repository.ExpenseRepository;
import jp.co.hoge.repository.IncomeRepository;

@Controller
public class InsertController {

	
	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private HttpSession session;
	@Autowired
	private CategoryRepository categoryRepository;

	
	
	@GetMapping("/insert")
	public String showInsertPage(Model model) {
	    model.addAttribute("categoryList", categoryRepository.findAll());
	    return "insert";
	}

	
	@GetMapping("/income/confirm")
	public String showIncomeConfirmPage() {
	    return "incomeConfirm";
	}

	@PostMapping("/income/confirm")
	public String incomeConfirm(@RequestParam("incomeDate") String date,
	                            @RequestParam("incomeAmount") String amount,
	                            HttpSession session) {

	    if (date == null || date.isEmpty() || amount == null || amount.isEmpty() || !amount.matches("\\d+")) {
	        session.setAttribute("incomeError", "日付と金額は必須です。金額は数字のみで入力してください。");
	        session.setAttribute("incomeDate", date);
	        session.setAttribute("incomeAmount", amount);
	        return "redirect:/insert?tab=income";  // ← タブ指定を追加
	    }

	    session.setAttribute("incomeDate", date);
	    session.setAttribute("incomeAmount", amount);
	    session.removeAttribute("incomeError");
	    return "incomeConfirm";
	}


	@PostMapping("/insert/complete")
	public String insertComplete() {
		User user = (User) session.getAttribute("user");
		Long userId = user.getUserId();

		if (session.getAttribute("incomeDate") != null) {
			Income income = new Income();
			income.setUserId(userId);
			income.setDate(LocalDate.parse((String) session.getAttribute("incomeDate"))); // 修正
			income.setAmount(new BigDecimal((String) session.getAttribute("incomeAmount")));
			income.setCreateAt(LocalDateTime.now());
			income.setUpdateAt(LocalDateTime.now());
			incomeRepository.save(income);
		} else if (session.getAttribute("expenseDate") != null) {
			Expense expense = new Expense();
			expense.setUserId(userId);
			expense.setDate(LocalDate.parse((String) session.getAttribute("expenseDate"))); // 修正
			expense.setAmount(new BigDecimal((String) session.getAttribute("expenseAmount")));
			expense.setCategoryId(Integer.parseInt((String) session.getAttribute("categoryId")));
			expense.setCreateAt(LocalDateTime.now());
			expense.setUpdateAt(LocalDateTime.now());
			expenseRepository.save(expense);
		}

		// セッション削除
		session.removeAttribute("incomeDate");
		session.removeAttribute("incomeAmount");
		session.removeAttribute("expenseDate");
		session.removeAttribute("expenseAmount");
		session.removeAttribute("categoryId");

		return "insertComplete";
	}

	@GetMapping("/expense/confirm")
	public String showExpenseConfirmPage(Model model) {
	    String categoryId = (String) session.getAttribute("categoryId");
	    String categoryName = "その他";

	    try {
	        Integer id = Integer.parseInt(categoryId);
	        categoryRepository.findById(id).ifPresent(category -> {
	            model.addAttribute("categoryName", category.getCategoryName());
	        });
	    } catch (NumberFormatException e) {
	        model.addAttribute("categoryName", categoryName);
	    }

	    // 日付の整形
	    String original = (String) session.getAttribute("expenseDate");
	    if (original != null) {
	        String formatted = original.replace("-", "/");
	        model.addAttribute("formattedExpenseDate", formatted);
	    }

	    return "expenseConfirm";
	}


	@PostMapping("/expense/confirm")
	public String expenseConfirm(
	        @RequestParam("expenseDate") String date,
	        @RequestParam("expenseAmount") String amount,
	        @RequestParam("categoryId") String categoryId,
	        HttpSession session,
	        Model model) {

	    if (date == null || date.isEmpty() || amount == null || amount.isEmpty() || !amount.matches("\\d+")) {
	        session.setAttribute("expenseError", "日付と金額は必須です。金額は数字のみで入力してください。");
	        session.setAttribute("expenseDate", date);
	        session.setAttribute("expenseAmount", amount);
	        session.setAttribute("categoryId", categoryId);
	        return "redirect:/insert?tab=expense";  // ← タブ指定を追加
	    }

	    session.setAttribute("expenseDate", date);
	    session.setAttribute("expenseAmount", amount);
	    session.setAttribute("categoryId", categoryId);
	    session.removeAttribute("expenseError");

	    String formattedDate = date.replace("-", "/");
	    model.addAttribute("formattedExpenseDate", formattedDate);

	    String categoryName = categoryRepository.findById(Integer.parseInt(categoryId))
	                            .map(cat -> cat.getCategoryName())
	                            .orElse("その他");
	    model.addAttribute("categoryName", categoryName);

	    return "expenseConfirm";
	}

	
	//「メニューへ戻る」で入力セッションを削除
	@GetMapping("/insert/cancel")
	public String cancelInsert() {
	    session.removeAttribute("incomeDate");
	    session.removeAttribute("incomeAmount");
	    session.removeAttribute("expenseDate");
	    session.removeAttribute("expenseAmount");
	    session.removeAttribute("categoryId");
	    session.removeAttribute("incomeError");
	    session.removeAttribute("expenseError");

	    return "redirect:/menu";
	}
}
