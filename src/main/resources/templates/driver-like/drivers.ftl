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
<h1>Drivers list</h1>
<table>
    <tr>
        <th>Номер телефона</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Машина</th>
    </tr>

<#list drivers as driver>
    <tr>
        <td><a href="/drivers/${driver.id}">${driver.phoneNumber}</a></td>
        <td>${driver.firstName}</td>
        <td>${driver.lastName}</td>
        <td><#list cars as car>
            <!--иф вложить в <a href по carId если хотим ссылаться на машину -->
            <#if driver.carId == car.id> ${car.model} ${car.number}</#if>
        </#list></td>

    </tr>
</#list>
</table>
<a href="/drivers/create">Create driver</a>
</body>
</html>
