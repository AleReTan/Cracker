<!DOCTYPE html>
<html lang="en">
<head>
    <title>Создание нового заказа</title>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript">
        </script>
    <meta charset="UTF-8">

    <script type="text/javascript">
    var moscow_map,
            piter_map;

    ymaps.ready(function(){
        moscow_map = new ymaps.Map("first_map", {
            center: [55.76, 37.64],
            zoom: 10
        });
    });
    </script>
</head>
<body>
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
    <input title="Стоимость" type="text" name="orderCosr">
    <p>Геолокация</p>
    <input title="Геолокация" type="text" name="geoData">
    <p>Статус заказа</p>
    <input title="Статус заказа" type="text" name="statusOrder">
    <div id="first_map" style="width:400px; height:300px"></div>
    <input type="submit" value="Создать">
</form>



</body>
</html>