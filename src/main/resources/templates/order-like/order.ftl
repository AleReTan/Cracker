<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Info</title>

</head>
<body>
<h1>Информация по заказу</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${order.id}</td>
    </tr>
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
        <th>Местоположение</th>
        <td>${order.geoData}</td>
    </tr>
    <tr>
        <th>Время заказа</th>
        <td>${(order.orderStartTime)!"sda"}</td>
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
        <td><select name="driverId" required form="updateOrder">
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

<br>
<a href="/orders">Back</a>
<form id="updateOrder" action="/orders/${order.id}" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name="name" value="${order.name}">
    <input type="hidden" name="typeId" value="${order.typeId}">
    <input type="hidden" name="clientFirstName" value="${order.clientFirstName}">
    <input type="hidden" name="clientLastName" value="${order.clientLastName}">
    <input type="hidden" name="clientPhoneNumber" value="${order.clientPhoneNumber}">
    <input type="hidden" name="address" value="${order.address}">
    <input type="hidden" name="orderCost" value="${order.orderCost}">
    <input type="hidden" name="geoData" value="${order.geoData}">
    <input type="hidden" name="orderStartTime" value="${order.orderStartTime}">
    <input type="hidden" name = "orderEndTime" value="${(order.orderEndTime)!"Заказ не завершен"}">
    <input type="hidden" name="statusOrder" value="${order.statusOrder}">
    <input type="submit" value="Сохранить"/>
</form>
<form id="deleteOrder" action="/orders/${order.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="Удалить"/>
</form>
</body>
</html>