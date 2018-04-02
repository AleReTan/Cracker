<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Info</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <!--<link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/table.css">-->
    <script>
        // This variable can be accessed from js
        var statusOrderFMVariable = "${order.statusOrder}";
        var selectedDriverId = "${(selectedDriver.id)!}";
        var startPoint = "${order.geoData}";
        var destinationPoint = "${order.destinationGeoData}";
    </script>
    <style>
        #map {
            width: 50%;
            height: 50%;
            padding: 0;
            margin: 0;
            size: 100px;
            position: absolute;
            left: 600px;
            top: 50px;
        }
    </style>
    <script type="text/javascript" src="/js/orderScript.js"></script>


</head>
<body>
<header class="user__header">
    <h1>Информация по заказу</h1>
    <header>
        <div>
            <table>
                <tr>
                    <th>Имя</th>
                    <td>${order.clientFirstName}</td>
                </tr>
                <tr>
                    <th>Фамилия</th>
                    <td>${order.clientLastName}</td>
                </tr>
                <tr>
                    <th>Адрес</th>
                    <td>${order.address}</td>
                </tr>
                <tr>
                    <th>Телефон</th>
                    <td>${order.clientPhoneNumber}</td>
                </tr>
                <tr>
                    <th>Местоположение заказчика</th>
                    <td>${order.geoData}</td>
                </tr>
                <tr>
                    <th>Пункт назначения</th>
                    <td>${order.destinationGeoData}</td>
                </tr>
                <tr>
                    <th>Время заказа</th>
                    <td>${order.orderStartTime}</td>
                </tr>
                <tr>
                    <th>Время окончания заказа</th>
                    <td>${(order.orderEndTime)!"Заказ не завершен"}</td>
                </tr>
                <th>Статус заказ</th>
                <td>${order.statusOrder}</td>
                </tr>

                <tr>
                    <th>Водитель эвакуатора</th>
                    <td><select name="driverId" id="driverSelect" form="changeDriver">
        <#if (selectedDriver.id)??>
            <option value="${(selectedDriver.id)}"
                    selected>${(selectedDriver.firstName)} ${(selectedDriver.lastName)}</option>
        </#if>
        <#list drivers as driver>
            <option value="${driver.id}"
                    <#if order.driverId == driver.id>selected</#if> >${driver.firstName} ${driver.lastName}</option>
        </#list>
                    </select></td>
                </tr>
                <tr>
                    <th>Cтоимость заказа</th>
                    <td>${order.orderCost}</td>
                </tr>
            </table>
        </div>
        <br>
        <a href="/orders">Back</a>
        <!-- ифы наружу, внутри формы, внутри запускать на разные URL, на сервисе методы водитель прибыл, закончил заказ-->
<#if order.statusOrder == "Поиск водителя"><input type="button" id="chooseDriver" value="Подобрать водителя"/></#if>
<#if order.statusOrder == "Водитель движется к клиенту">
<form id="pickClient" action="/orders/${order.id}/pickclient" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name="name" value="${order.name}">
    <input type="hidden" name="typeId" value="${order.typeId}">
    <input type="hidden" name="clientFirstName" value="${order.clientFirstName}">
    <input type="hidden" name="clientLastName" value="${order.clientLastName}">
    <input type="hidden" name="clientPhoneNumber" value="${order.clientPhoneNumber}">
    <input type="hidden" name="address" value="${order.address}">
    <input type="hidden" name="orderCost" value="${order.orderCost}">
    <input type="hidden" name="geoData" value="${order.geoData}">
    <input type="hidden" name="destinationGeoData" value="${order.destinationGeoData}">
    <input type="hidden" name="orderStartTime" value="${order.orderStartTime}">
    <input type="hidden" name="orderEndTime" value="${order.orderEndTime}">
    <input type="hidden" name="driverId" value="${order.driverId}">
    <input type="submit" id="saveButton" value="Забрал клиента"/>
</form>
    <#elseif order.statusOrder == "Водитель с клиентом">
    <form id="closeOrder" action="/orders/${order.id}/closeorder" method="post">
        <input type="hidden" name="_method" value="patch"/>
        <input type="hidden" name="name" value="${order.name}">
        <input type="hidden" name="typeId" value="${order.typeId}">
        <input type="hidden" name="clientFirstName" value="${order.clientFirstName}">
        <input type="hidden" name="clientLastName" value="${order.clientLastName}">
        <input type="hidden" name="clientPhoneNumber" value="${order.clientPhoneNumber}">
        <input type="hidden" name="address" value="${order.address}">
        <input type="hidden" name="orderCost" value="${order.orderCost}">
        <input type="hidden" name="geoData" value="${order.geoData}">
        <input type="hidden" name="destinationGeoData" value="${order.destinationGeoData}">
        <input type="hidden" name="orderStartTime" value="${order.orderStartTime}">
        <input type="hidden" name="orderEndTime" value="${order.orderEndTime}">
        <input type="hidden" name="driverId" value="${order.driverId}">
        <input type="submit" id="saveButton" value="Завершить заказ"/>
    </form>
    </#if>

        <form id="changeDriver" action="/orders/${order.id}" method="post">
            <input type="hidden" name="_method" value="patch"/>
            <input type="hidden" name="name" value="${order.name}">
            <input type="hidden" name="typeId" value="${order.typeId}">
            <input type="hidden" name="clientFirstName" value="${order.clientFirstName}">
            <input type="hidden" name="clientLastName" value="${order.clientLastName}">
            <input type="hidden" name="clientPhoneNumber" value="${order.clientPhoneNumber}">
            <input type="hidden" name="address" value="${order.address}">
            <input type="hidden" name="orderCost" value="${order.orderCost}">
            <input type="hidden" name="geoData" value="${order.geoData}">
            <input type="hidden" name="destinationGeoData" value="${order.destinationGeoData}">
            <input type="hidden" name="orderStartTime" value="${order.orderStartTime}">
            <input type="hidden" name="orderEndTime" value="${order.orderEndTime}">
            <input type="hidden" name="statusOrder" value="${order.statusOrder}">
    <#if order.statusOrder == "Водитель с клиентом" ||
    order.statusOrder == "Заказ завершен" ||
    order.statusOrder == "Заказ отменен" >
        <input type="submit" id="setDriver" disabled hidden value="Установить водителя"/>
    <#else>
        <input type="submit" id="setDriver" disabled value="Установить водителя"/>
    </#if>
        </form>

        <form id="cancelOrder" action="/orders/${order.id}/cancelorder" method="post">
            <input type="hidden" name="_method" value="patch"/>
            <input type="hidden" name="name" value="${order.name}">
            <input type="hidden" name="typeId" value="${order.typeId}">
            <input type="hidden" name="clientFirstName" value="${order.clientFirstName}">
            <input type="hidden" name="clientLastName" value="${order.clientLastName}">
            <input type="hidden" name="clientPhoneNumber" value="${order.clientPhoneNumber}">
            <input type="hidden" name="address" value="${order.address}">
            <input type="hidden" name="orderCost" value="${order.orderCost}">
            <input type="hidden" name="geoData" value="${order.geoData}">
            <input type="hidden" name="destinationGeoData" value="${order.destinationGeoData}">
            <input type="hidden" name="orderStartTime" value="${order.orderStartTime}">
            <input type="hidden" name="orderEndTime" value="${order.orderEndTime}">
    <#if    order.statusOrder != "Водитель с клиентом" &&
    order.statusOrder != "Заказ завершен" &&
    order.statusOrder != "Заказ отменен" >
        <input type="submit" id="cancelButton" value="Отменить заказ"/>
    </#if>
        </form>

        <form id="deleteOrder" action="/orders/${order.id}" method="post">
            <input type="hidden" name="_method" value="delete"/>
            <input type="submit" value="Удалить"/>
        </form>
<div id="map"></div>
</body>
</html>
