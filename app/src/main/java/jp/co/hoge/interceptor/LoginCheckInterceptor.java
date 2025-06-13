package jp.co.hoge.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ログイン状態確認
        HttpSession session = request.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // URI取得
        String uri = request.getRequestURI();

        // ログイン不要のパス（静的リソースやログインページなど）
        if (uri.startsWith("/login") || uri.startsWith("/signup")) {
            return true;
        }

        // ログインしてなければリダイレクト
        if (!isLoggedIn) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }
}
