package jp.co.hoge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.entity.User;
import jp.co.hoge.service.AuthService;

@Controller
public class QuitController {

    private final AuthService authService;

    public QuitController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/quit")
    public String showQuitForm() {
        return "quit";
    }

    @PostMapping("/quit")
    public String processQuit(@RequestParam("password") String password,
                              HttpSession session,
                              Model model) {
        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            return "redirect:/login";
        }

        boolean isValid = authService.verifyPassword(sessionUser.getUserId(), password);
        if (!isValid) {
            model.addAttribute("error", "パスワードが正しくありません。");
            return "quit";
        }

        authService.deleteUserById(sessionUser.getUserId());
        session.invalidate(); // セッション無効化

        return "quitComplete";
    }
}
