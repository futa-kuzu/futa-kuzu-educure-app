package jp.co.hoge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jp.co.hoge.entity.User;
import jp.co.hoge.service.AuthService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AuthService authService;
	
	@GetMapping("/menu")
	public String adminMenu(HttpSession session) {
	    User user = (User) session.getAttribute("user");
	    if (user == null || user.getRoleId() != 1) {
	        return "redirect:/login";
	    }
	    return "adminMenu";
	}
	
	//利用者一覧表示
	@GetMapping("/userList")
	public String showUserList(Model model) {
		List<User> userList = authService.findAllUsers();
		model.addAttribute("userList", userList);
		return "userAll";
	}

}

