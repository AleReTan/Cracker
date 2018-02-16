<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">

</head>
<body>
 <h1> Список всех пользователей </h1>
<form   action="/admin/users/createUser" method="Get">
    <input id="createUser"  type="submit" value="Добавить"/>
</form>
 <!--Таблица пользователей-->
<table border="1">
    <tr>
        <th>Логин</th>
        <th>Роль</th>

    </tr>
<#list allUsers as user>
    <tr>
        <th><a href="/admin/users/${user.login}" > ${user.login}</a> </th>
        <th>${user.role}</th>
    </tr>
</#list>

</table>
<a href="/admin" > Назад </a> </th>
</body>
</html>