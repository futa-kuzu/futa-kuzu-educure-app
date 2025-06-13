package jp.co.hoge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.DTO.MenuInfoDto;
import jp.co.hoge.entity.User;
import jp.co.hoge.service.MenuService;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;

    // メニュー画面表示
    @GetMapping("/menu")
    public String showMenu(HttpSession session, Model model) {

        // セッションからユーザー情報を取得
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login"; // ユーザーがログインしていない場合、ログイン画面にリダイレクト
        }

        // ユーザーIDを取得
        Long userId = user.getUserId();

        // 今月の収支と目標収支を取得
        MenuInfoDto menuInfoDto = menuService.getMenuInfo(userId);

        // モデルにDTOを追加
        model.addAttribute("menuInfo", menuInfoDto);

        return "menu"; // menu.jspに遷移
    }
}
