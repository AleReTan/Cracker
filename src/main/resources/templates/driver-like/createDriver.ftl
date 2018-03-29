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
    <p>Автомобиль</p>
    <select name="carId">
        <#if cars?size != 1> <b>Нет свободных машин</b></#if>
        <option value="0" selected>Выберите машину</option>
            <#list cars as car>
                <option value="${car.id}"> ${car.model} ${car.number}</option>
            </#list>
    </select>

    <input type="hidden" name="typeId" value="8">
    <input type="hidden" name = "onShift" value="false">
    <input type="hidden" name="driverGeoData" value="0.0,0.0">
    <input type="submit" value="Создать">
</form>
</body>
</html>