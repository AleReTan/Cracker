<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание нового водителя</title>

</head>
<body>
<form name="driver" action="/drivers/create" method="post">
    <p>Название</p>
    <input title="Название" type="text" name="name">
    <p>Имя</p>
    <input title="Имя" type="text" name="firstName">
    <p>Фамилия</p>
    <input title="Фамилия" type="text" name="lastName">
    <p>Телефон</p>
    <input title="Телефон" type="text" name="phoneNumber">
    <p>Айди автомобиля</p><!--Мб допилить с подгрузкой всех машин или выпилить вообще, чтобы изначально 0 был-->
    <input title="айди автомобиля" type="text" name="carId">
    <input type="hidden" name = "typeId" value="8">
    <input type="submit" value="Создать">
</form>

</body>
</html>