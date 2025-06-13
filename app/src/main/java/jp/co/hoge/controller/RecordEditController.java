package jp.co.hoge.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.hoge.entity.Expense;
import jp.co.hoge.entity.Income;
import jp.co.hoge.repository.ExpenseRepository;
import jp.co.hoge.repository.IncomeRepository;
import jp.co.hoge.service.CategoryService;
import jp.co.hoge.service.ExpenseService;
import jp.co.hoge.service.IncomeService;

@Controller
@RequestMapping("/record")
public class RecordEditController {

    @Autowired
    private IncomeService incomeService;

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private IncomeRepository incomeRepository;

    // 収入編集画面表示
    @GetMapping("/income/edit")
    public String showIncomeEditForm(@RequestParam("id") Long id, Model model) {
        Income income = incomeService.findById(id);
        model.addAttribute("income", income);
        return "incomeEdit";
    }

    // 支出編集画面表示
    @GetMapping("/expense/edit")
    public String showExpenseEditForm(@RequestParam("id") Long id, Model model, HttpSession session) {
        Expense sessionExpense = (Expense) session.getAttribute("tempExpense");

        if (sessionExpense != null && sessionExpense.getExpenseId().equals(id)) {
            model.addAttribute("expense", sessionExpense);
            session.removeAttribute("tempExpense");
        } else {
            Expense expense = expenseService.findById(id);
            model.addAttribute("expense", expense);
        }

        model.addAttribute("categories", categoryService.findAll());
        return "expenseEdit";
    }

    // 収入確認画面
    @PostMapping("/income/edit/confirm")
    public String confirmIncomeEdit(
        @Valid @ModelAttribute Income income,
        BindingResult bindingResult,
        Model model,
        HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "incomeEdit";
        }

        // セッションに一時保存（戻るボタン用）
        session.setAttribute("tempIncome", income);

        model.addAttribute("income", income);
        return "incomeEditConfirm";
    }

    // 支出確認画面
    @PostMapping("/expense/edit/confirm")
    public String confirmExpenseEdit(
        @Valid @ModelAttribute Expense expense,
        BindingResult bindingResult,
        Model model,
        HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "expenseEdit";
        }

        // カテゴリ名をセット
        String categoryName = categoryService.findNameById(expense.getCategoryId());
        expense.setCategoryName(categoryName);

        // セッションに保存
        session.setAttribute("tempExpense", expense);

        model.addAttribute("expense", expense);
        return "expenseEditConfirm";
    }


    // 収入更新完了画面
    @PostMapping("/income/edit/complete")
    public String completeIncomeEdit(@RequestParam("incomeId") Long incomeId,
    								 @RequestParam("amount") BigDecimal amount,
    								 @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    								 HttpSession session
    ) {
        Long userId = (Long) session.getAttribute("userId");
        
        Income income = incomeRepository.findById(incomeId).orElse(null);
        if(income != null && income.getUserId().equals(userId)) {
        	income.setAmount(amount);
        	income.setDate(date);
        	income.setUpdateAt(LocalDateTime.now());
        	
        	incomeRepository.save(income);
        }
        return "editComplete";
    }

    // 支出更新完了画面
    @PostMapping("/expense/edit/complete")
    public String completeExpenseEdit(@RequestParam("expenseId") Long expenseId,
    								  @RequestParam("amount") BigDecimal amount,
    								  @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    								  @RequestParam("categoryId") int categoryId,
    								  HttpSession session
    ) {
    	Long userId = (Long) session.getAttribute("userId");
    	
    	
        Expense expense = expenseRepository.findById(expenseId).orElse(null);
        if(expense != null && expense.getUserId().equals(userId)) {
        	expense.setAmount(amount);
        	expense.setDate(date);
        	expense.setCategoryId(categoryId);
        	expense.setUpdateAt(LocalDateTime.now());
        	
        	expenseRepository.save(expense);
        }
        return "editComplete";
    }
    
    // 収入削除処理
    @PostMapping("/income/delete")
    public String deleteIncome(@RequestParam("incomeId") Long incomeId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Income income = incomeRepository.findById(incomeId).orElse(null);

        if (income != null && income.getUserId().equals(userId)) {
            incomeRepository.delete(income);
        }

        return "redirect:/record";
    }

    // 支出削除処理
    @PostMapping("/expense/delete")
    public String deleteExpense(@RequestParam("expenseId") Long expenseId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Expense expense = expenseRepository.findById(expenseId).orElse(null);

        if (expense != null && expense.getUserId().equals(userId)) {
            expenseRepository.delete(expense);
        }

        return "redirect:/record";
    }
    
    //戻るボタン用の処理
    //収入
    @GetMapping("/income/edit/confirm/back")
    public String backToIncomeEditFromConfirm(@RequestParam("incomeId") Long id, HttpSession session, Model model) {
        Income sessionIncome = (Income) session.getAttribute("tempIncome");

        if (sessionIncome != null && sessionIncome.getIncomeId().equals(id)) {
            model.addAttribute("income", sessionIncome);
            session.removeAttribute("tempIncome");
        } else {
            Income income = incomeService.findById(id);
            model.addAttribute("income", income);
        }

        return "incomeEdit";
    }
    
    //支出
    @GetMapping("/expense/edit/confirm/back")
    public String backToExpenseEditFromConfirm(@RequestParam("expenseId") Long id, HttpSession session, Model model) {
        Expense sessionExpense = (Expense) session.getAttribute("tempExpense");

        if (sessionExpense != null && sessionExpense.getExpenseId().equals(id)) {
            model.addAttribute("expense", sessionExpense);
            session.removeAttribute("tempExpense");
        } else {
            Expense expense = expenseService.findById(id);
            model.addAttribute("expense", expense);
        }

        model.addAttribute("categories", categoryService.findAll());
        return "expenseEdit";
    }

}
