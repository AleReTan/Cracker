<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Car info</title>

</head>
<body>
<h1>Информация по автомобилю</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${car.id}</td>
    </tr>
    <tr>
        <th>Название</th>
        <td>${car.name}</td>
    </tr>
    <tr>
        <th>Номер</th>
        <td> <input title="Номер" type="text" required form="updateCars" name="number" value=${car.number}> </td>
    </tr>
    <tr>
        <th>Модель</th>
        <td>${car.model}</td>
    </tr>
    <tr>
        <th>Цвет</th>
        <td> <input title="Цвет" type="text" required form="updateCars" name="color" value=${car.color}> </td>
    </tr>
    <tr>
        <th>Тип</th>
        <td>${car.type}</td>
    </tr>
</table>
<br>

<form id="updateCars" action="/cars/${car.id}" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name = "name" value="${car.name}">
    <input type="hidden" name = "typeId" value="${car.typeId}">
    <input type="hidden" name = "model" value="${car.model}">
    <input type="hidden" name = "type" value="${car.type}">
    <input type="submit" value="Сохранить" />
</form>
<form id="deleteCars" action="/cars/${car.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="Удалить" />
</form>
<a href="/cars">Back</a>

</body>
</html>