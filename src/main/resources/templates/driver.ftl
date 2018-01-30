<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Driver info</title>

</head>
<body>
<h1>Информация по водителю</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${diver.id}</td>
    </tr>
    <tr>
        <th>Имя</th>
        <td>${diver.firstName}</td>
    </tr>
    <tr>
        <th>Фамилия</th>
        <td>${diver.lastName}</td>
    </tr>
    <tr>
        <th>Телефон</th>
        <td>${diver.mobPhone}</td>
    </tr>
    <tr>
        <th>Автомобиль</th>
        <td>${diver.carId}</td>
    </tr>
</table>
<br>
<a href="/divers">Back</a>

</body>
</html>