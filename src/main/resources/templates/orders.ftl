<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Orders list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Название</th>
        <th>Адрес</th>
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
        <td>${order.driverId}</td>
        <td>${order.orderStartTime}</td>
        <td>${order.statusOrder}</td>
        <td>${order.orderCost}</td>
    </tr>
</#list>
</table>
<a href="/createOrder">Создадим заказ</a>
</body>
</html>
