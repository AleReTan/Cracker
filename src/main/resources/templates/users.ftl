<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <h1> Список всех пользователей </h1>
</head>
<body>

<!--Таблица пользователей-->
<table border="1">
    <tr>
        <th>Логин</th>
        <th>Роль</th>
    </tr>
<#list allUsers as user>
    <tr>
        <th><a href="users/${user.login}" > ${user.login} </th>
        <th>${user.role}</th>
    </tr>
</#list>

</table>

</body>
</html>