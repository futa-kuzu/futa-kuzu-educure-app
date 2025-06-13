package jp.co.hoge.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.entity.Goal;
import jp.co.hoge.entity.User;
import jp.co.hoge.repository.GoalRepository;

@Controller
public class GoalController {

    @Autowired
    private HttpSession session;
    @Autowired
    private GoalRepository goalRepository;

    //目標設定フォームへ
    @GetMapping("/goal")
    public String showGoalForm(Model model) {
        // セッションからgoalを取得してフォームに渡す
        String goal = (String) session.getAttribute("goal");
        model.addAttribute("goal", goal);
        return "goalForm";  // goalForm.jspに遷移
    }

    //入力確認画面へ
    @PostMapping("/goal/confirm")
    public String confirmGoal(@RequestParam("goal") String goal, Model model) {
        if (goal == null || goal.trim().isEmpty()) {
            // エラーメッセージを表示
            model.addAttribute("errorMessage", "入力は必須です");
            return "goalForm";  // エラー時には再度goalForm.jspに戻る
        }

        // セッションにgoalを保存
        session.setAttribute("goal", goal);
        model.addAttribute("goal", goal);  // 確認画面に目標金額を渡す
        return "goalConfirm";  // goalConfirm.jspに遷移
    }

    //完了画面へ
    @PostMapping("/goal/complete")
    public String completeGoal() {
    	
    	//userIdを取得
    	User user = (User) session.getAttribute("user");
    	if (user == null) {
    	    return "redirect:/login";
    	}
    	Long userId = user.getUserId();

    	// セッションから目標金額を取得
    	String goalStr = (String) session.getAttribute("goal");
        if (goalStr == null || goalStr.trim().isEmpty()) {
            return "redirect:/goal";
        }
        
        // 目標金額をBigDecimalに変換
        BigDecimal amount;
        try {
            amount = new BigDecimal(goalStr.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            return "redirect:/goal";  // 数字以外の入力があった場合、再度フォームに戻す
        }

    	// 既存の目標を取得
    	Goal goal = goalRepository.findByUserId(userId).orElse(new Goal());
    	
    	// ユーザーIDを設定して目標金額を保存
    	goal.setUserId(userId);
    	goal.setAmount(amount);
    	goal.setUpdateAt(LocalDateTime.now());
    	
    	if (goal.getCreateAt() == null) {
    		goal.setCreateAt(LocalDateTime.now());
    	}
    	
    	goalRepository.save(goal);
    	
    	// セッションから一時データの削除
    	session.removeAttribute("goal");
    	
        // 完了画面に遷移
        return "goalComplete";  // goalComplete.jspに遷移
    }
    
    // セッションのgoalを削除してメニューに戻る
    @GetMapping("/goal/cancel")
    public String backToMenu() {
        session.removeAttribute("goal");
        return "redirect:/menu";
    }

}
