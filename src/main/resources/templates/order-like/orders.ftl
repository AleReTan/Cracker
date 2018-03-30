<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:700,400' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="css/loginStyle.css">
    <link rel="stylesheet" href="css/buttons.css">

</head>
<body>
    <header class="user__header">
<h1 style="color: azure">Список заказов</h1>
</header>


<div   class="pagedown">
<table>
    <tr>
        <th>Номер</th>
        <th>Название</th>
        <th>Адрес местоположения клиента</th>
        <th>Водитель</th>
        <th>Время заказа</th>
        <th>Cтатус</th>
        <th>Стоимость</th>
    </tr>
<#list orders as order>
    <tr >
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
</div>
    <div>
        <a class="button" href="/orders/create">Создать заказ</a>
    </div>

</body>
</html>
