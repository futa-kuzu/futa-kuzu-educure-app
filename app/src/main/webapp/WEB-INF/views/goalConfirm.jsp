<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>目標設定｜確認画面</title>
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
    <h1>確認画面</h1>
    <form action="/goal/complete" method="post">
        <div class="confirm-wrapper">
            <label for="goal-income">目標収支</label>
            <!-- 確認画面でもgoalを表示 -->
            <input type="text" readonly value="+ ${goal != null ? goal : ''} 円">
        </div>

        <div class="form-actions">
            <a href="/goal" class="button-outline button-common">入力画面へ戻る</a>
            <button type="submit" class="button-common">完了画面へ進む</button>
        </div>
    </form>
</body>
</html>

