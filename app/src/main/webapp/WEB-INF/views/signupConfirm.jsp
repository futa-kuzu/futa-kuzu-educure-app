<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>新規登録｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>確認画面</h1>
    
    <form:form action="${pageContext.request.contextPath}/signup/complete" method="post" modelAttribute="signupForm">
        <div class="confirm-wrapper">
            <label>ユーザーID</label>
            <form:input path="loginId" readonly="true"/>
        </div>
        <div class="confirm-wrapper">
            <label>メールアドレス</label>
            <form:input path="email" readonly="true"/>
        </div>
        <div class="confirm-wrapper">
            <label>名前</label>
            <form:input path="userName" readonly="true"/>
        </div>
        <div class="confirm-wrapper">
            <label>パスワード</label>
            <input type="text" readonly value="・・・・・・"/>
        </div>

        <label>パスワード再入力</label>
		<form:password path="confirmPassword"/>
			<c:if test="${not empty confirmPasswordError}">
    			<p class="error">${confirmPasswordError}</p>
			</c:if>
		<form:errors path="confirmPassword" cssClass="error"/>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/signup/back" class="button-outline button-common">入力画面へ戻る</a>
            <button type="submit" class="button-common">登録する</button>
        </div>
    </form:form>
</body>
</html>
