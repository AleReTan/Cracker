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
    <link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/tabAll.css">
    <link rel="stylesheet" href="/css/menu.css">
</head>
<body>

<div>
    <header class="user__header">
        <h1 style="color: azure">Список всех пользователей </h1>
    </header>
</div>
<div>
    <nav role='navigation'>
        <ul>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
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
<input class="form_button" type="button" href="/admin" value="Назад"/>
</body>
</html>
