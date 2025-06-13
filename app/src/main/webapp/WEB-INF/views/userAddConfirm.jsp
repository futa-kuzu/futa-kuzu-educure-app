<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ユーザー登録｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <h1>確認画面</h1>
    
    <c:if test="${not empty confirmPasswordError}">
    	<p class="error">${confirmPasswordError}</p>
	</c:if>
    

    <form action="${pageContext.request.contextPath}/admin/user/complete" method="post">
        <input type="hidden" name="loginId" value="${signupForm.loginId}">
        <input type="hidden" name="email" value="${signupForm.email}">
        <input type="hidden" name="userName" value="${signupForm.userName}">
        <input type="hidden" name="password" value="${signupForm.password}">

        <div class="confirm-wrapper">
            <label>ユーザーID</label>
            <input type="text" readonly value="${signupForm.loginId}">
        </div>
        <div class="confirm-wrapper">
            <label>メールアドレス</label>
            <input type="text" readonly value="${signupForm.email}">
        </div>
        <div class="confirm-wrapper">
            <label>名前</label>
            <input type="text" readonly value="${signupForm.userName}">
        </div>
        <div class="confirm-wrapper">
            <label>パスワード</label>
            <input type="text" readonly value="●●●●●●●●">
        </div>

        <label class="label-confirm-password">パスワード再入力</label>
        <input type="password" name="confirmPassword">

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/admin/user/back" class="button-outline button-common">入力画面へ戻る</a>
            <button type="submit" class="button-common">登録する</button>
        </div>
    </form>
</body>
</html>
