<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>管理者メニュー</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <div class="admin-menu-title">
        <h1>管理者メニュー</h1>
        <p>ログインユーザー : 
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    ${sessionScope.user.userName}
                </c:when>
                <c:otherwise>
                    不明なユーザー
                </c:otherwise>
            </c:choose>
        </p>
    </div>

    <div class="menu-action-button">
        <a href="${pageContext.request.contextPath}/admin/userList">利用者一覧</a>
        <a href="${pageContext.request.contextPath}/admin/user/add">管理者ユーザー登録</a>
        <a href="${pageContext.request.contextPath}/logout">ログアウト</a>
        <a href="${pageContext.request.contextPath}/quit">退会</a>
    </div>
</body>
</html>
