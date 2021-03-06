<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/tablecars.css">
    <link rel="stylesheet" href="/css/menu.css">
    <link rel="stylesheet" href="/css/createOrderButton.css">
</head>
<body>

<div>
    <header class="user__header">
        <h2 style="color: azure">Список всех пользователей </h2>
    </header>
</div>
<div>
    <nav role='navigation'>
        <ul>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
        <#if roles== "ADMIN">
            <li><a onclick="location.href='/admin/users'">Пользователи</a></li>
        </#if>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>

 <!--Таблица пользователей-->
<table >
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
<#if roles== "ADMIN">
<input class="button" type="button" onclick="location.href='/admin/users/createUser';" value="Добавить пользователя"/>
</#if>
</body>
</html>
