<!DOCTYPE html>
<html lang="ru">
<head>
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
        <h1 style="color: azure">Страница администратора</h1>
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
    </nav>
</div>


<div class="user__header">
    <input class="form_button" onclick="location.href='/drivers/create';" type="button" value="Добавить водителя"/>
    <input class="form_button" type="button" onclick="location.href='/cars/create';" value="Добавить машину"/>
    <input class="form_button" type="button" onclick="location.href='/admin/users/createUser';" value="Добавить пользователя"/>

</div>

</body>
</html>


