<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/tabAll.css">
    <link rel="stylesheet" href="/css/menu.css">
</head>
<body>
<div>
    <header class="user__header">
        <h1 style="color: azure">Информация о машине</h1>
    </header>
</div>
<div>
    <nav role='navigation'>
        <ul>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers';">Водители</a></li>
            <li><a onclick="location.href='/cars';">Машины</a></li>
            <li><a onclick="location.href='/logout';">Выйти</a></li>
        </ul>
    </nav>
</div>

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
        <td><input title="Номер" type="text" required form="updateCars" name="number" value=${car.number}></td>
    </tr>
    <tr>
        <th>Модель</th>
        <td>${car.model}</td>
    </tr>
    <tr>
        <th>Цвет</th>
        <td><input title="Цвет" type="text" required form="updateCars" name="color" value=${car.color}></td>
    </tr>
    <tr>
        <th>Тип</th>
        <td>${car.type}</td>
    </tr>
</table>
<br>

<form id="updateCars" action="/cars/${car.id}" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name="name" value="${car.name}">
    <input type="hidden" name="typeId" value="${car.typeId}">
    <input type="hidden" name="model" value="${car.model}">
    <input type="hidden" name="type" value="${car.type}">
    <input class="form_button" type="submit" value="Сохранить"/>
</form>

<form id="deleteCars" action="/cars/${car.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input class="form_button" name="delete" type="submit" value="Удалить"/>
</form>

</body>
</html>
