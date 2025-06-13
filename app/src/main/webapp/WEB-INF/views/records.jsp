<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>収支データ</title>
    <link rel="stylesheet" href="../../css/style.css" />
    <link rel="stylesheet" href="../../css/insert.css" />
    <link rel="stylesheet" href="../../css/data.css" />
</head>
<body>

<h1>収支データ</h1>

<!-- タブ切替ボタン -->
<div class="tab-buttons">
    <button id="income-tab" class="active">収入</button>
    <button id="expense-tab">支出</button>
</div>

<!-- 収入タブ -->
<div id="income-form">
    <div class="month-select">
        <a href="?year=${prevYear}&month=${prevMonth}">&lt;</a>
        <p>${year}年${month}月</p>
        <a href="?year=${nextYear}&month=${nextMonth}">&gt;</a>
    </div>
    <p class="income-all"><fmt:formatNumber value="${totalIncome}" type="number" groupingUsed="true" maxFractionDigits="0" /> 円</p>
    <table class="income-table">
        <thead>
            <tr><th>日付</th><th>金額</th><th></th><th></th></tr>
        </thead>
        <tbody>
            <c:forEach items="${incomes}" var="income">
                <tr>
                    <td>${income.date}</td>
                    <td><fmt:formatNumber value="${income.amount}" type="number" groupingUsed="true" maxFractionDigits="0" />円</td>
                    <td><a href="/record/income/edit?id=${income.incomeId}"><img src="/images/correction.svg" alt="編集" /></a></td>
                    <td><img src="/images/delete.svg" alt="削除" class="delete-icon" data-id="${income.incomeId}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- 支出タブ -->
<div id="expense-form" style="display: none;">
    <div class="month-select">
        <!-- ←支出の月送りリンクはtab=expenseをつける -->
        <a href="?year=${prevYear}&month=${prevMonth}&tab=expense">&lt;</a>
        <p>${year}年${month}月</p>
        <a href="?year=${nextYear}&month=${nextMonth}&tab=expense">&gt;</a>
    </div>

    <div class="expense-chart">
        <canvas id="expensePieChart"></canvas>
    </div>

    <table class="expense-table">
        <thead>
            <tr><th>日付</th><th>金額</th><th>カテゴリ</th><th></th><th></th></tr>
        </thead>
        <tbody>
            <c:forEach items="${expenses}" var="expense">
                <tr>
                    <td>${expense.date}</td>
                    <td><fmt:formatNumber value="${expense.amount}" type="number" groupingUsed="true" maxFractionDigits="0" />円</td>
                    <td>${expense.categoryName}</td>
                    <td><a href="/record/expense/edit?id=${expense.expenseId}"><img src="/images/correction.svg" alt="編集" /></a></td>
                    <td><img src="/images/delete.svg" alt="削除" class="delete-icon" data-id="${expense.expenseId}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- 収入削除ポップアップ -->
<div id="income-delete-popup" class="popup hidden">
    <div class="popup-content">
        <p>この収入データを削除しますか？</p>
        <form id="income-delete-form" method="post" action="/record/income/delete">
            <input type="hidden" name="incomeId" id="delete-income-id" />
            <div class="popup-buttons">
                <button type="button" id="cancel-delete" class="delete-button">キャンセル</button>
                <button type="submit" class="delete-button">削除</button>
            </div>
        </form>
    </div>
</div>

<!-- 支出削除ポップアップ -->
<div id="expense-delete-popup" class="popup hidden">
    <div class="popup-content">
        <p>この支出データを削除しますか？</p>
        <form id="expense-delete-form" method="post" action="/record/expense/delete">
            <input type="hidden" name="expenseId" id="delete-expense-id" />
            <div class="popup-buttons">
                <button type="button" id="cancel-delete-expense" class="delete-button">キャンセル</button>
                <button type="submit" class="delete-button">削除</button>
            </div>
        </form>
    </div>
</div>

<div class="form-actions">
    <a href="/menu" class="button-outline button-common">メニュー画面へ戻る</a>
</div>

<script>
    const labels = [];
    const data = [];

    <c:forEach items="${categoryTotals}" var="entry">
        data.push(${entry.value});
        labels.push("${entry.key}");
    </c:forEach>
</script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/insertTab.js"></script>
<script src="/js/expenseChart.js"></script>

</body>
</html>
