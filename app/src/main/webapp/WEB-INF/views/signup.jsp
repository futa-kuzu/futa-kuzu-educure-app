<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規登録</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>新規登録</h1>
    <form:form action="${pageContext.request.contextPath}/signup/confirm" method="post" modelAttribute="signupForm">
        <label>ユーザーID</label>
        <form:input path="loginId" cssClass="form-control"/>
        <form:errors path="loginId" cssClass="error"/>

        <label>メールアドレス</label>
        <form:input path="email" cssClass="form-control"/>
        <form:errors path="email" cssClass="error"/>

        <label>名前</label>
        <form:input path="userName" cssClass="form-control"/>
        <form:errors path="userName" cssClass="error"/>

        <label>パスワード</label>
        <form:password path="password" cssClass="form-control"/>
        <form:errors path="password" cssClass="error"/>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/login" class="button-outline button-common">ログイン画面へ戻る</a>
            <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form:form>
</body>
</html>
