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
        <td>${driver.carId}</td>
    </tr>
</#list>
</table>
</body>
</html>
