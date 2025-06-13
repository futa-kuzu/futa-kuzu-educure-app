<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>収入データ修正｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>確認画面</h1>

<form action="${pageContext.request.contextPath}/record/income/edit/complete" method="post">
	<input type="hidden" name="incomeId" value="${income.incomeId}">
    <input type="hidden" name="date" value="${income.date}">
    <input type="hidden" name="amount" value="${income.amount}">

    <div class="confirm-wrapper">
        <label>日付</label>
        <input type="text" readonly id="date-display" value="${income.date}">
    </div>
    <div class="confirm-wrapper">
        <label>金額</label>
        <input type="text" readonly id="amount-display" value="${income.amount}円">
    </div>

    <div class="form-actions">
        <a href="${pageContext.request.contextPath}/record/income/edit/confirm/back?incomeId=${income.incomeId}" class="button-outline button-common">入力画面へ戻る</a>
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
