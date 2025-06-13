<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>メニュー</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>目標達成率</h1>

    <div class="chart-container">
        <svg class="progress-ring" width="275" height="275">
            <circle class="circle-bg" cx="137.5" cy="137.5" r="110" />
            <circle class="circle-fg" cx="137.5" cy="137.5" r="110" />
        </svg>
        <div class="percentage-text">
    		<c:choose>
    			<c:when test="${menuInfo.achievementRate >= 100}">
        			達成
    			</c:when>
   				<c:otherwise>
        			<fmt:formatNumber 
            		value="${menuInfo.achievementRate < 0 ? 0 : menuInfo.achievementRate}" 
            		type="number" 
           			maxFractionDigits="0" 
            		groupingUsed="false"
        			/>%
    			</c:otherwise>
			</c:choose>
		</div>
    </div>

    <div class="menu-dashboard">
        <div class="balance-wrapper data-wrapper">
            <div class="data">
                <p>収入</p>
                <p><fmt:formatNumber value="${menuInfo.incomeTotal}" type="number" groupingUsed="true" /> 円</p>
            </div>
            <div class="data">
                <p>支出</p>
                <p><fmt:formatNumber value="${menuInfo.expenseTotal}" type="number" groupingUsed="true" /> 円</p>
            </div>
            <div class="data">
                <p>収支</p>
                <p><fmt:formatNumber value="${menuInfo.balance}" type="number" groupingUsed="true" /> 円</p>
            </div>
        </div>
        <span class="line"></span>
        <div class="goal-wrapper data-wrapper">
            <div class="data">
                <p>目標収支</p>
                <p>+ <fmt:formatNumber value="${menuInfo.goalAmount}" type="number" groupingUsed="true" /> 円</p>
            </div>
            <div class="data">
                <p>目標達成まで</p>
                <p>
        			<c:choose>
            			<c:when test="${menuInfo.remainingToGoal <= 0}">
                			達成
            			</c:when>
            			<c:otherwise>
                			あと <fmt:formatNumber value="${menuInfo.remainingToGoal}" type="number" groupingUsed="true"/> 円
            			</c:otherwise>
        			</c:choose>
    			</p>
            </div>
        </div>
    </div>

    <div class="menu-action-button">
        <a href="${pageContext.request.contextPath}/insert">収入/支出を記録する</a>
        <a href="${pageContext.request.contextPath}/goal">目標を立てる</a>
        <a href="${pageContext.request.contextPath}/record">記録を見る</a>
        <a href="${pageContext.request.contextPath}/logout">ログアウト</a>
        <a href="${pageContext.request.contextPath}/quit">退会</a>
    </div>

    <script src="${pageContext.request.contextPath}/js/menuChart.js"></script>
</body>
</html>
