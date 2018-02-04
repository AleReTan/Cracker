<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <h1> Данные пользователя </h1>
</head>
<body>

<form>
<table border="0" cellpadding="5" cellspacing="10">
    <tr>
        <td>Логин:</td>
        <td><input type="text" value="${userData.login}"></td>
    </tr>
    <tr>
        <td>Роль:</td>
        <td><input type="text" value="${userData.role}"></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Отправить" >
            <input type="submit" value="Назад" formaction="admin/users" formmethod="get">
        </td>
    </tr>
</table>
</form>

</body>
</html>