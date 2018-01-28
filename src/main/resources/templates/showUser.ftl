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
    <td>${order.firstName}</td>
    </tr>
    <tr>
        <th>Фамилия</th>
        <td>${order.lastName}</td>
    </tr>
    <tr>
        <th>Адрес</th>
        <td>${order.address}</td>
    </tr>
    <tr>
        <th>Телефон</th>
        <td>${order.mobPhone}</td>
    </tr>
    <tr>
        <th>Автомобиль</th>
        <td>${order.modelCar}</td>
    </tr>
    <tr>
        <th>Цвет автомобиля</th>
        <td>${order.color}</td>
    </tr>
    <tr>
        <th>Номер автомобиля</th>
        <td>${order.numberAuto}</td>
    </tr>
    <tr>
        <th>Водитель эвакуатора</th>
        <td>${order.drivers}</td>
    </tr>
    <tr>
        <th>Тариф</th>
        <td>${order.rate}</td>
    </tr>
    <tr>
        <th>Cтоимость заказа</th>
        <td>${order.costOrder}</td>
    </tr>
</table>

<br>
<a href="/orders">Back</a>

</body>
</html>