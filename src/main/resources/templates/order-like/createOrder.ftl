<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <script type="text/javascript" src="/js/geoMap.js"></script>
    <script type="text/javascript" src="/js/creatingOrder.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" href="/css/loginStyle.css">
    <link rel="stylesheet" href="/css/formfirst.css">
    <style>
        #popup {
            background: #ffb;
            padding: 10px;
            border: 2px solid #999;
        }
    </style>
    <script type="text/javascript" src="/js/creatingOrder.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

</head>
<body >
<div>
<header class="user__header">
    <h1 style="color: azure">Создать заказ</h1>
</header>
    </div>
<div>
    <nav role='navigation'>
        <ul>
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>

</head>
<body>
<div class="popup" id="popup">
    <span id="popup-content">Text in Popup</span>
</div>
<script>$('#popup').hide(0)</script>
</div id="container">

</div class= "pagedown">
<form class="contact-form" name="order" action="JavaScript:catcher()">
    <div>
    <input title="Название" type="hidden" placeholder="Название" name="name" value=" ">
    </div>

    <div>
        <input title="Имя" type="text" id="clientFirstName" placeholder="Имя" name="clientFirstName">
    </div>

   <div>
    <input title="Фамилия" type="text" placeholder="Фамилия" name="clientLastName">
       <div>

    <div>
        <input title="Фамилия" type="text" id="clientLastName" placeholder="Фамилия" name="clientLastName">
        <div>

            <div>
                <input title="Телефон клиента" type="text" id="clientPhoneNumber" placeholder="Телефон клиента"
                       name="clientPhoneNumber">
            </div>
            <div>
                <input title="Стоимость" id="orderCost" placeholder="Стоимость" type="text" name="orderCost">

    <div>
    <input title="Геолокация" id="geo" type="text" placeholder="Местоположение заказчика" name="geoData">
        </div>

                <div>
                    <input title="Геолокация" id="geoData" type="text" placeholder="Местоположение заказчика"
                           name="geoData">
                </div>

                <div>
                    <input title="Геолокация" id="destinationGeoData" placeholder="Местоположение пункта назначения"
                           type="text" name="destinationGeoData">
                </div>
            </details>
            <br/>

     <#if drivers?size == 0 > <b>Нет свободных водителей</b></#if>
    <select name="driverId" id="driverSelect">
        <option value="0" selected>Выберите водителя</option>
            <#list drivers as driver>
                <option value="${driver.id}"> ${driver.lastName} ${driver.phoneNumber}</option>
            </#list>
    </select>

    <div>
    <input id="chooseDriver" type="button" class="form_button" value="Подобрать водителя"/>
    <input type="hidden" name="typeId" value="6">
    <input class="form_button" type="submit" value="Создать"/>
    <input class="form_button" type="button" onclick="history.back();" value="Назад"/>
        </div>

</form>
</div>
</div>

<div id="map"></div>
</body>
</html>
