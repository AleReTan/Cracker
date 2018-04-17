<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Dispath service of towling</title>
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/tabAll.css">
    <link rel="stylesheet" href="/css/menu.css">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script>
        // This variable can be accessed from js
        var selectedCarId = "${(selectedCar.id)!}";
    </script>
    <script type="text/javascript" src="/js/driverScript.js"></script>
</head>
<body>
<header class="user__header">
    <h1 style="color: azure">Информация по водителю</h1>
</header>
<div>
    <nav role='navigation'>
        <ul>
        <#if roles== "ADMIN">
            <li><a onclick="location.href='/admin'">Главная</a></li>
        </#if>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
        <#if roles== "ADMIN">
            <li><a onclick="location.href='/admin/users'">Пользователи</a></li>
        </#if>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>

<table>
    <tr>
        <th>ID</th>
        <td>${driver.id}</td>
    </tr>
    <tr>
        <th>Имя</th>
        <td>${driver.firstName}</td>
    </tr>
    <tr>
        <th>Фамилия</th>
        <td>${driver.lastName}</td>
    </tr>
    <tr>
        <th>Телефон</th>
        <td>${driver.phoneNumber}</td>
    </tr>
    <tr>
        <th>Геоданные</th>
        <td>${(driver.driverGeoData)!"0.0,0.0"}</td>
    </tr>
    <tr>
        <th>Автомобиль</th>
        <td><select name="carId" id="driverId" form="updateDriver">
        <#if (selectedCar.id)??>
            <option value="${(selectedCar.id)}"
                    selected>${(selectedCar.model)} ${(selectedCar.number)}</option>
        </#if>
        <#list cars as car>
            <option value="${car.id}"
                    <#if driver.carId == car.id>selected</#if> > ${car.model} ${car.number}</option>
        </#list>
        </select></td>

    </tr>
</table>
<br>
<form id="updateDriver" action="/drivers/${driver.id}" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name="name" value="${driver.name}">
    <input type="hidden" name="typeId" value="${driver.typeId}">
    <input type="hidden" name="firstName" value="${driver.firstName}">
    <input type="hidden" name="lastName" value="${driver.lastName}">
    <input type="hidden" name="phoneNumber" value="${driver.phoneNumber}">
    <input type="hidden" name="driverGeoData" value="${(driver.driverGeoData)!"0.0,0.0"}">
    <input type="hidden" name="onShift" value="${(driver.onShift)}">
    <input type="hidden" name="login" value="${(driver.login)}">
    <input class="form_button" type="submit" id="saveButton" disabled value="Сохранить"/>
</form>
<#if roles = "ADMIN">
<form id="deleteDriver" action="/drivers/${driver.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input class="form_button" type="submit" value="Удалить"/>

</form>
</#if>
</body>
</html>
