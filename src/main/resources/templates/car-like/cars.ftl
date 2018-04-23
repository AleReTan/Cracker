<!DOCTYPE html>
<head xmlns="http://www.w3.org/1999/html">
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="css/tableMini.css">
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body>
<div>
    <header class="user__header">
        <h1 style="color: azure">Список машин</h1>
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

<div>
    <table>
        <tr>
            <th>Название</th>
            <th>Номер</th>
            <th>Модель</th>
        </tr>
<#list cars as car>
    <tr>
        <td><a href="/cars/${car.id}">${car.name}</a></td>
        <td>${car.number}</td>
        <td>${car.model}</td>
    </tr>
</#list>
    </table>
</div>
</body>
</html>
