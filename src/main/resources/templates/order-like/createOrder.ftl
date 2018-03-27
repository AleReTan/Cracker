<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript" src="/js/geoMap.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <style>
        html, body, #map {
            width: 90%;
            height: 90%;
            padding: 0;
            margin: 0;
        }
    </style>
    <meta charset="UTF-8">

</head>
<body>

<form name="order" action="/orders/create" method="post">

    <p>Название</p>
    <input title="Название" type="text" name="name">

    <p>Имя</p>
    <input title="Имя" type="text" name="clientFirstName">

    <p>Фамилия</p>
    <input title="Фамилия" type="text" name="clientLastName">

    <p>Телефон</p>
    <input title="Телефон клиента" type="text" name="clientPhoneNumber">

<details>
    <summary>Раскрыть адрес</summary>
    <p>Адрес</p>
    <input title="Адрес" id="address" type="text" name="address">

    <p>Местоположение заказчика</p>
    <input title="Геолокация" id="geo" type="text" name="geoData">

        <p>Местоположение пункта назначения</p>
        <input title="Геолокация" id="destinationGeo" type="text" name="destinationGeoData">
    </details>

    <p>Стоимость</p>
    <input title="Стоимость" id="price" type="text" name="orderCost">

    <p>Водитель</p>
    <select name="driverId" id="driverSelect">
        <option>Выберите водителя</option>
            <#list drivers as driver>
                <option value="${driver.id}"> ${driver.lastName} ${driver.phoneNumber}</option>
            </#list>
    </select>


    <!--затестить кто встанет на заказ если ткнуть кнопку подбор водителя, мб отлавливать тык кнопки и не обращать внимание на выпадашку-->
    <p>Статус заказа</p>
        <p><select name="statusOrder">
            <option selected="selected">Выберите статус</option>
            <option value="Поиск водителя">Поиск водителя</option>
            <option value="Водитель движется к клиенту">Водитель движется к клиенту</option>
            <option value="Водитель с клиентом">Водитель с клиентом</option>
            <option value="Заказ завершен">Заказ завершен</option>
            <option value="Заказ отменен">Заказ отменен</option>
        </select></p>
    <input type="hidden" name="typeId" value="6">
    <input type="submit" value="Создать">
</form>
<input type="button" id="chooseDriver" value="Подобрать водителя"/>
<div id="map"></div>
</body>
</html>
