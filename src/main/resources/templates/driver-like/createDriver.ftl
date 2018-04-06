<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/formfirst.css">
    <link rel="stylesheet" href="/css/menu.css">

</head>
<body>

<div>
    <header class="user__header">
        <h1 style="color: azure">Добавление водителя</h1>
    </header>
</div>
<div>
    <nav role='navigation'>
        <ul>
            <li><a onclick="location.href='/admin'">Главная</a></li>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
            <li><a onclick="location.href='/admin/users'">Пользователи</a></li>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>

<form name="driver" action="/drivers/create" method="post">
    <input title="Название"  placeholder="Название" type="text" name="name">
    <input title="Имя" type="text" placeholder="Имя"  name="firstName">
    <input title="Фамилия" type="text" placeholder="Фамилия"  name="lastName">
    <input title="Телефон" type="text" placeholder="Телефон" name="phoneNumber">

    <select name="carId">
        <#if cars?size != 1> <b>Нет свободных машин</b></#if>
        <option value="0" selected>Выберите машину</option>
            <#list cars as car>
                <option value="${car.id}"> ${car.model} ${car.number}</option>
            </#list>
    </select>

    <input type="hidden" name="typeId" value="8">
    <input type="hidden" name="driverGeoData" value="0.0,0.0">
    <input class="form_button" type="submit" value="Создать">
</form>
</body>
</html>
