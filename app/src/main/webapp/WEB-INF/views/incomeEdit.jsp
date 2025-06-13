<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>収入データを修正</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert.css">
</head>
<body>
<h1>収入データを修正</h1>

<form:form id="income-form" action="/record/income/edit/confirm" method="post" modelAttribute="income">
    <form:hidden path="incomeId"/>

    <label for="date">日付</label>
	<input type="date" id="date" name="date" value="${income.date}">
	<form:errors path="date" cssClass="error" />


    <label for="amount">金額</label>
    <form:input path="amount" id="amount"/>
    <form:errors path="amount" cssClass="error" />

    <div class="form-actions">
        <a href="/record" class="button-outline button-common">一覧へ戻る</a>
        <button type="submit" class="button-common">確認画面へ進む</button>
    </div>
</form:form>

<script src="/js/insertTab.js"></script>
<script src="/js/formatAmount.js"></script>
<script>
  setupAmountFormatter("income-form", "amount");
</script>
</body>
</html>
