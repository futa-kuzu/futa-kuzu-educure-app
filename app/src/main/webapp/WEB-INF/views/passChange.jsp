<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>パスワード変更</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>パスワード変更</h1>

    <form:form action="${pageContext.request.contextPath}/passChange/confirm" method="post" modelAttribute="passChangeForm">
        <label for="loginIdOrEmail">ユーザーIDまたはメールアドレス</label>
        <form:input path="loginIdOrEmail" cssClass="form-control" />
        <form:errors path="loginIdOrEmail" cssClass="error" />

        <label for="newPassword">新規パスワード</label>
        <form:password path="newPassword" cssClass="form-control" />
        <form:errors path="newPassword" cssClass="error" />

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/login" class="button-outline button-common">ログイン画面へ戻る</a>
            <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form:form>
</body>
</html>
