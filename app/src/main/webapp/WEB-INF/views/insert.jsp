<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>収支を記録</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/insert.css">
</head>
<body>
    <h1>収支を記録</h1>

    <!-- タブ切り替えボタン -->
    <div class="tab-buttons">
        <button id="income-tab" class="active">収入</button>
        <button id="expense-tab">支出</button>
    </div>

    <!-- 収入フォーム（初期表示） -->
    <form id="income-form" action="${pageContext.request.contextPath}/income/confirm" method="post">
        <label>日付</label>
        <input type="date" name="incomeDate" value="${sessionScope.incomeDate}">
        <label>金額</label>
        <input type="text" name="incomeAmount" value="${sessionScope.incomeAmount}">
        <c:if test="${not empty incomeError}">
    		<p class="error">${incomeError}</p>
		</c:if>
        
        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/insert/cancel" class="button-outline button-common">メニュー画面へ戻る</a>
            <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form>

    <!-- 支出フォーム（非表示） -->
    <form id="expense-form" action="${pageContext.request.contextPath}/expense/confirm" method="post" style="display: none;">
        <label>日付</label>
        <input type="date" name="expenseDate" value="${sessionScope.expenseDate}">
        <label>金額</label>
        <input type="text" name="expenseAmount" value="${sessionScope.expenseAmount}">
        <c:if test="${not empty expenseError}">
    		<p class="error">${expenseError}</p>
		</c:if>
        <label>カテゴリ</label>
        <select name="categoryId">
    		<c:forEach var="category" items="${categoryList}">
        		<option value="${category.categoryId}"
            		${sessionScope.categoryId == category.categoryId ? 'selected' : ''}>
            		${category.categoryName}
        		</option>
    		</c:forEach>
		</select>


        
        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/insert/cancel" class="button-outline button-common">メニュー画面へ戻る</a>
            <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form>

    <script src="${pageContext.request.contextPath}/js/insertTab.js"></script>
</body>
</html>