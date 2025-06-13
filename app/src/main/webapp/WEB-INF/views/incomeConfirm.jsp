<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>収入を記録｜確認画面</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

	<%
    	String original = (String) session.getAttribute("incomeDate");
    	String formatted = original != null ? original.replace("-", "/") : "";
    	request.setAttribute("formattedIncomeDate", formatted);
	%>

    <h1>確認画面</h1>
    <form action="${pageContext.request.contextPath}/insert/complete" method="post">
        <div class="confirm-wrapper">
            <label>日付</label>
            <input type="text" readonly value="${formattedIncomeDate}">
        </div>
        <div class="confirm-wrapper">
            <label>金額</label>
            <input type="text" readonly value="${sessionScope.incomeAmount}円">
        </div>

        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/insert" class="button-outline button-common">入力画面へ戻る</a>
            <button type="submit" class="button-common">登録する</button>
        </div>
    </form>
</body>
</html>
