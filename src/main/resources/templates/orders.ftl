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
        <th>Имя</th>
        <th>фамилия</th>
        <th>Адрес</th>
        <th>Телефон</th>
        <th>Автомобиль</th>
        <th>Цвет</th>
        <th>Номер автомобиля</th>
        <th>Email</th>
        <th></th>
    </tr>
<#list orders as order>
    <tr>
        <td><a href="/orders/${order.id}">${order.id}</a></td>
        <td>${order.firstName}</td>
        <td>${order.lastName}</td>
        <td>${order.address}</td>
        <td>${order.mobPhone}</td>
        <td>${order.modelCar}</td>
        <td>${order.color}</td>
        <td>${order.numberAuto}</td>
        <td>${order.email}</td>

    </tr>
</#list>
</table>
<a href="/addOrder">Create order</a>
</body>
</html>
