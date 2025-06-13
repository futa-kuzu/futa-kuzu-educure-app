<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ユーザー登録</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <h1>管理者ユーザー登録</h1>

    <form:form action="${pageContext.request.contextPath}/admin/user/confirm" modelAttribute="signupForm" method="post">
        <label>ユーザーID</label>
        <form:input path="loginId" />
        <form:errors path="loginId" cssClass="error" />

        <label>メールアドレス</label>
        <form:input path="email" />
        <form:errors path="email" cssClass="error" />

        <label>名前</label>
        <form:input path="userName" />
        <form:errors path="userName" cssClass="error" />

        <label>パスワード</label>
        <form:password path="password" />
        <form:errors path="password" cssClass="error" />

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/admin/menu" class="button-outline button-common">メニュー画面へ戻る</a>
            <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form:form>
</body>
</html>
