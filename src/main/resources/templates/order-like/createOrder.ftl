<!DOCTYPE html>
<html lang="en">
<head>
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

</head>
<body>
</div id="container">
<div class="underline">
</div class= "pagedown">
<form action="#" class="contact-form" name="order" action="/orders/create" method="post">
    <div>
    <input title="Название" type="hidden" placeholder="Название" name="name" value=" ">
    </div>

    <div>

    <input title="Имя" type="text" placeholder="Имя" name="clientFirstName">
        </div>

   <div>
    <input title="Фамилия" type="text" placeholder="Фамилия" name="clientLastName">
       <div>

    <div>
    <input title="Телефон клиента" type="text" placeholder="Телефон клиента" name="clientPhoneNumber">
        </div>
     <div>
        <input title="Стоимость" id="price" placeholder="Стоимость" type="text" name="orderCost">

     </div>
           <details> <br/>
           <div>
    <input title="Адрес" id="address" placeholder="Адрес" type="text" name="address">
        </div>

    <div>
    <input title="Геолокация" id="geo" type="text" placeholder="Местоположение заказчика" name="geoData">
        </div>

    <div>
    <input title="Геолокация" id="destinationGeo" placeholder="Местоположение пункта назначения" type="text" name="destinationGeoData">
        </div>
       </details > <br/>

     <#if drivers?size == 0 > <b>Нет свободных водителей</b></#if>
    <select name="driverId" id="driverSelect">
        <option value="0" selected>Выберите водителя</option>
            <#list drivers as driver>
                <option value="${driver.id}"> ${driver.lastName} ${driver.phoneNumber}</option>
            </#list>
    </select>
    <input id="form_button" type="button" id="chooseDriver" value="Подобрать водителя"/>

    <!--затестить кто встанет на заказ если ткнуть кнопку подбор водителя, мб отлавливать тык кнопки и не обращать внимание на выпадашку-->
    <!--<p>Статус заказа</p>

    <p><select name="statusOrder">
        <option selected>Выберите статус</option>
        <option value="Поиск водителя">Поиск водителя</option>
        <option value="Водитель движется к клиенту">Водитель движется к клиенту</option>
        <option value="Водитель с клиентом">Водитель с клиентом</option>
        <option value="Заказ завершен">Заказ завершен</option>
        <option value="Заказ отменен">Заказ отменен</option>
    </select></p>-->
    <input type="hidden" name="typeId" value="6">

    <button id="form_button" type="submit">Создать</button>
</form>
<input id="form_button" type="button" onclick="history.back();" value="Назад"/>

</div>


<div id="map"></div>
</body>
</html>
