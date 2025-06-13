<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>支出データを修正</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert.css">
</head>
<body>
<h1>支出データを修正</h1>

<form:form id="expense-form" action="/record/expense/edit/confirm" method="post" modelAttribute="expense">
    <form:hidden path="expenseId"/>

    <label for="date">日付</label>
    <input type="date" id="date" name="date" value="${expense.date}">
    <form:errors path="date" cssClass="error" />

    <label for="amount">金額</label>
    <form:input path="amount" id="amount"/>
    <form:errors path="amount" cssClass="error" />

    <label for="category">カテゴリ</label>
    <form:select path="categoryId" id="category">
        <form:options items="${categories}" itemValue="categoryId" itemLabel="categoryName"/>
    </form:select>
    <form:errors path="categoryId" cssClass="error" />

    <div class="form-actions">
        <a href="/record" class="button-outline button-common">一覧へ戻る</a>
        <button type="submit" class="button-common">確認画面へ進む</button>
    </div>
</form:form>

<script src="/js/formatAmount.js"></script>
<script>
  setupAmountFormatter("expense-form", "amount");
</script>
</body>
</html>
