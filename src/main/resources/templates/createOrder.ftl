<!DOCTYPE html>
<html lang="en">
<head>
    <title>Создание нового заказа</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="geoMap.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        html,body, #map {
            width: 70%; height: 70%; padding: 0; margin: 0;

        }
    </style>
    <meta charset="UTF-8">

</head>
<body>
<div id = "fotm">
    <form name="order" action="/createOrder" method="post">
        <p>Имя</p>
        <input title="Имя" type="text" name="clientFirstName">
        <p>Фамилия</p>
        <input title="Фамилия" type="text" name="clientLastName">
        <p>Телефон</p>
        <input title="Телефон клиента" type="text" name="clientPhoneNumber">
        <p>Адрес</p>
        <input title="Адрес" type="text" name="address">
        <p>Стоимость</p>
        <input title="Стоимость" type="text" name="orderCost">
        <p>Геолокация</p>
        <input title="Геолокация" id="maps" type="text"  name="geoData" >
        <p>Статус заказа</p>
        <input title="Статус заказа" type="text" name="statusOrder">
        <input type="submit" value="Создать">
    </form>
</div>
<div id="map"></div>
</body>
</html>