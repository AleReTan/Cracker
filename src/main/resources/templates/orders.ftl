<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h1>Orders list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Адрес</th>
        <th>Водитель</th>
        <th>Время заказа</th>
        <th>Тариф</th>
        <th>Стоимость</th>
        <th>Info</th>

    </tr>
<#list orders as order>
    <tr>
        <td>${order.id}</td>
        <td>${order.address}</td>
        <td>${order.drivers}</td>
        <td>${order.timeOrder}</td>
        <td>${order.rate}</td>
        <td>${order.costOrder}</td>
        <td><a href="/orders/${order.id}">info</a></td>
    </tr>
</#list>
</table>
<a href="/addOrder">Create order</a>
</body>
</html>
