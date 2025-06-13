<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>パスワード変更｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>確認画面</h1>

	<c:if test="${not empty confirmPasswordError}">
    	<p class="error">${confirmPasswordError}</p>
	</c:if>

    <form:form action="${pageContext.request.contextPath}/passChange/complete" method="post" modelAttribute="passChangeForm">
        <div class="confirm-wrapper">
            <label>ユーザーIDまたはメールアドレス</label>
            <input type="text" readonly value="${passChangeForm.loginIdOrEmail}" />
        </div>
        <div class="confirm-wrapper">
            <label>新規パスワード</label>
            <input type="text" readonly value="●●●●●●●●" />
        </div>

        <label class="label-confirm-password">パスワード再入力</label>
        <form:password path="confirmPassword" cssClass="form-control" />
        <form:errors path="confirmPassword" cssClass="error" />

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/passChange/back" class="button-outline button-common">入力画面へ戻る</a>
            <button type="submit" class="button-common">パスワードを変更</button>
        </div>
    </form:form>
</body>
</html>
