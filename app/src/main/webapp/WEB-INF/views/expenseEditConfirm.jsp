<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>支出データ修正｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>支出 確認画面</h1>

<form action="${pageContext.request.contextPath}/record/expense/edit/complete" method="post">
    <!-- hidden項目 -->
    <input type="hidden" name="expenseId" value="${expense.expenseId}">
    <input type="hidden" name="date" value="${expense.date}">
    <input type="hidden" name="amount" value="${expense.amount}">
    <input type="hidden" name="categoryId" value="${expense.categoryId}">

    <div class="confirm-wrapper">
        <label>日付</label>
        <input type="text" readonly id="date-display" value="${expense.date}">
    </div>
    <div class="confirm-wrapper">
        <label>金額</label>
        <input type="text" readonly id="amount-display" value="${expense.amount}円">
    </div>
    <div class="confirm-wrapper">
        <label>カテゴリ</label>
        <input type="text" readonly value="${expense.categoryName}">
    </div>

    <div class="form-actions">
        <a href="${pageContext.request.contextPath}/record/expense/edit/confirm/back?expenseId=${expense.expenseId}" class="button-outline button-common">入力画面へ戻る</a>
        <button type="submit" class="button-common">登録する</button>
    </div>
</form>

<script>
    window.addEventListener("DOMContentLoaded", () => {
        const dateInput = document.getElementById("date-display");
        const rawDate = dateInput.value;
        if (rawDate.includes("-")) {
            const parts = rawDate.split("-");
            dateInput.value = parts[0] + "/" + parts[1] + "/" + parts[2];
        }

        const amountInput = document.getElementById("amount-display");
        const rawAmount = amountInput.value.replace(/[^\d]/g, "");
        if (rawAmount) {
            amountInput.value = Number(rawAmount).toLocaleString() + "円";
        }
    });
</script>

</body>
</html>
