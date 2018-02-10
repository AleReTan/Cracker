<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание нового автомобиля</title>
</head>

<body>
<form name="cars" action="/cars/addCar" method="post">
    <p>Название</p>
    <input title="Название" type="text" name="name">
    <p>Номер автомобиля</p>
    <input title="Номер автомобиля" type="text" name="number" value='example'>
    <p>Модель</p>
    <input title="Модель" type="text" name="model">
    <p>Цвет</p>
    <input title="Цвет" type="text" name="color">
    <p>Тип</p>
    <input title="Тип" type="text" name="type">
    <h2> </h2>
    <input type="submit" value="Создать">
</form>

</body>
</html>