<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="css/style.css">

    <meta charset="UTF-8">
</head>
<body>
<h1>Orders list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Название</th>
        <th>Адрес местоположения клиента</th>
        <th>Водитель</th>
        <th>Время заказа</th>
        <th>Cтатус</th>
        <th>Стоимость</th>
    </tr>
<#list orders as order>
    <tr>
        <td>${order.id}</td>
        <td><a href="/orders/${order.id}">${order.name}</a></td>
        <td>${order.address}</td>
        <td> <#list drivers as driver>
             <#if order.driverId == driver.id> <a href="/drivers/${order.driverId}">${driver.firstName} ${driver.lastName}</a></#if>
        </#list></td>
        <td>${order.orderStartTime}</td>
        <td>${order.statusOrder}</td>
        <td>${order.orderCost}</td>
    </tr>
</#list>
</table>
<a href="/orders/create">Создадим заказ</a>
</body>
</html>
