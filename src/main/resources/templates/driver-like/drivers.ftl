<!DOCTYPE html>
<html>
<head>
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript" src="/js/properties.js"></script>
    <script type="text/javascript" src="/js/showDrivers.js"></script>
    <link rel="stylesheet" href="css/menu.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/tableMini.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <style>
        #map {
            width: 70%;
            height: 30%;
            position: absolute;
            margin: 0;
            align-content: center;
            left: 122px;
            top: 350px;
        }
    </style>
</head>
<body>
<div>
    <header class="user__header">
        <h1 style="color: azure">Список водителей</h1>
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

<table>
    <tr>
        <th>Номер телефона</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Машина</th>
    </tr>

<#list drivers as driver>
    <tr>
        <td><a href="/drivers/${driver.id}">${driver.phoneNumber}</a></td>
        <td>${driver.firstName}</td>
        <td>${driver.lastName}</td>
        <td><#list cars as car>
            <#if driver.carId == car.id><a href="/cars/${driver.carId}"> ${car.model} ${car.number}</a></#if>
        </#list></td>
    </tr>
</#list>
</table>

<#if roles== "ADMIN">
<input class="form_button" onclick="location.href='/drivers/create';" type="button" value="Добавить водителя"/>
</#if>
<div id="map"></div>
</body>
</html>
