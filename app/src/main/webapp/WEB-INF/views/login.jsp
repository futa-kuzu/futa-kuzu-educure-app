<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ログイン</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>ログイン</h1>
    
    <c:if test="${not empty error}">
    	<div class="error-global error">${error}</div>
	</c:if>

    <form:form action="/login" method="post" modelAttribute="loginForm"  novalidate="novalidate">
        <label for="login_id">ユーザーIDまたはメールアドレス</label>
		<form:input id="login_id" path="loginIdOrEmail" cssClass="form-control" cssErrorClass="form-control error-input" />
		<form:errors path="loginIdOrEmail" cssClass="error" />

		<label for="password">パスワード</label>
		<form:password id="password" path="password" cssClass="form-control" cssErrorClass="form-control error-input" />
		<form:errors path="password" cssClass="error" />


        <div class="form-actions">
            <button type="submit" class="button-common">ログイン</button>
            <div class="link-group">
                <a href="${pageContext.request.contextPath}/passChange">パスワードをお忘れの方</a>
                <a href="/signup">はじめてご利用の方</a>
            </div>
        </div>
    </form:form>
</body>
</html>
