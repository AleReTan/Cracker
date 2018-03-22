<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="css/style.css">
    <meta charset="UTF-8">
</head>
<body>
<h1>Cars list</h1>
<table>
    <tr>
        <th>Название</th>
        <th>Номер</th>
        <th>Модель</th>
    </tr>
<#list cars as car>
    <tr>
        <td><a href="/cars/${car.id}">${car.name}</a></td>
        <td>${car.number}</td>
        <td>${car.model}</td>
    </tr>
</#list>
</table>
<a href="/cars/create">Create cars</a>
<h2> </h2>

</body>
</html>
