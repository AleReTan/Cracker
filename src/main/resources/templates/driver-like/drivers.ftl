<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript" src="js/showDrivers.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <style>
        html,body, #map {
            width: 100%; height: 100%; padding: 0; margin: 0;

        }
    </style>
</head>
<body>
<h1> <a href="/logout"> Logout</h1>
<h1>Drivers list</h1>
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
            <!--иф вложить в <a href по carId если хотим ссылаться на машину -->
            <#if driver.carId == car.id> ${car.model} ${car.number}</#if>
        </#list></td>

    </tr>
</#list>
</table>
<a href="/drivers/create">Create driver</a>
<div id="map"></div>

</body>
</html>
