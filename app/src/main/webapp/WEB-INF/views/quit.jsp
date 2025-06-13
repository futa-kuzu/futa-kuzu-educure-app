<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>退会</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>退会</h1>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/quit" method="post">
        <label for="password">パスワード</label>
        <input type="password" id="password" name="password">
        <a href="javascript:history.back();" class="button-outline button-common">戻る</a>
        <button type="submit" class="button-common">退会</button>
    </form>
</body>
</html>
