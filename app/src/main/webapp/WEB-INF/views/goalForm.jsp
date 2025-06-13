<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>目標設定</title>
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/goal.css">
</head>
<body>
    <h1>目標を立てる</h1>
    <form action="/goal/confirm" method="post">
        <label for="goal-income">目標収支を入力してください</label>
      
        <div class="input-group">
          <span class="prefix">+</span>
          <input type="text" id="goal-income" name="goal" value="${goal != null ? goal : ''}">
          <span class="suffix">円</span>
        </div>
        
        <c:if test="${not empty errorMessage}">
    		<p class="error">${errorMessage}</p>
		</c:if>
      
        <div class="form-actions">
          <a href="${pageContext.request.contextPath}/goal/cancel" class="button-outline button-common">メニュー画面へ戻る</a>
          <button type="submit" class="button-common">確認画面へ進む</button>
        </div>
    </form>
</body>
</html>
