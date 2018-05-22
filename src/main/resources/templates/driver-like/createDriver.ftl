<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/properties.js"></script>
    <script type="text/javascript" src="/js/creatingDriver.js"></script>
    <script type="text/javascript" src="/js/jquery.maskedinput-1.1.3.js"></script>
    <script type="text/javascript" src="/mask.js" ></script>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/formcreate.css">
    <link rel="stylesheet" href="/css/menu.css">
    <style>
        #popup {
            background: #ffb;
            padding: 10px;
            border: 2px solid #999;
        }
    </style>
    <div class="popup" id="popup">
        <span id="popup-content">Text in Popup</span>
    </div>
    <script>$('#popup').hide(0)</script>
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
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
            <li><a onclick="location.href='/admin/users'">Пользователи</a></li>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>
<form name="driver" action="JavaScript:createDriver()">
    <input title="Логин содержит латинские символы и цифры, минимум 3 " placeholder="Логин" id="login" pattern="[A-Za-z0-9]{3,}" type="text" name="login">
    <input title="Пароль должен содержать 6 символов" placeholder="Пароль" id="password"  maxlength="6" minlength="6" pattern="[0-9A-za-zА-Яа-яЁё]{6,}" type="password" name="password" /><br /><br />

    <input title="Имя содержит русские символы, минимум 3" type="text" id="firstName" placeholder="Имя" pattern="[А-Яа-яЁё]{3,}" name="firstName">
    <input title="Фамилия содержит русские символы, минимум 3" type="text" id="lastName" placeholder="Фамилия" pattern="[А-Яа-яЁё]{3,}" name="lastName">
    <input title="Телефон" type="text" id="phoneNumber" placeholder="Телефон" name="phoneNumber">
    <select id="carSelect" name="carId">
        <#if cars?size == 0> <b>Нет свободных машин</b></#if>
        <option value="0" selected>Выберите машину</option>
            <#list cars as car>
                <option value="${car.id}"> ${car.model} ${car.number}</option>
            </#list>
    </select>
    <input class="form_button" type="submit" value="Создать">
</form>

</body>
</html>
