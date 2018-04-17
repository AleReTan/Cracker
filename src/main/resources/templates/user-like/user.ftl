<!DOCTYPE html>
<html lang="rus">
<head>
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
    <h1 style="color: azure">Информация пользователя</h1>
</header>
</div>
<div>
    <nav role='navigation'>
        <ul>
        <#if roles== "ADMIN">
            <li><a onclick="location.href='/admin'">Главная</a></li>
        </#if>
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

<table>
    <tr>
        <th>Логин</th>
        <td>${userData.login}</td>
    </tr>
    <tr>
        <th>Пароль</th>
        <td>${userData.password}</td>
    </tr>
    <tr>
        <th>Роль</th>
        <td>${userData.role}</td>
    </tr>
</table>
<form id="deleteUser" action="/admin/users/${userData.login}" method="post">
    <input class="form_button" id="dlt" type="submit" value="Удалить" />
</form>
</body>
</html>
