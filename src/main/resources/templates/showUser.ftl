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
        <td>${order.orderStartTime}</td>
    </tr>
    <tr>
        <th>Статус заказ</th>
        <td>${order.statusOrder}</td>
    </tr>
    <tr>
        <th>Водитель эвакуатора</th>
        <td><select name="driverId" required form="selectDriver">
        <#list drivers as driver>
            <option value="${driver.id}">${driver.firstName} ${driver.lastName}</option>
        </#list>
        </select></td>
    </tr>
    <tr>
        <th>Cтоимость заказа</th>
        <td>${order.orderCost}</td>
    </tr>
</table>

<br>

<form id="selectDriver"  method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name = "id" value="${order.id}">
    <input type="hidden" name = "name" value="${order.name}">
    <input type="hidden" name = "clientFirstName" value="${order.clientFirstName}">
    <input type="hidden" name = "clientLastName" value="${order.clientLastName}">
    <input type="hidden" name = "address" value="${order.address}">
    <input type="hidden" name = "clientPhoneNumber" value="${order.clientPhoneNumber}">
    <input type="hidden" name = "geoData" value="$${order.geoData}">
    <input type="hidden" name = "orderStartTime" value="${order.orderStartTime}">
    <input type="hidden" name = "statusOrder" value="${order.statusOrder}">
    <input type="hidden" name = "driverId" value="${order.driverId}">
    <input type="hidden" name = "orderCost" value="${order.orderCost}">
    <a href="/orders">Back</a>
    <input type="submit" value="Сохранить" />
</form>

</body>
</html>