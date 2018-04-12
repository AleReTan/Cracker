<!DOCTYPE html>
<html lang="en">
<head>
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
        <h1 style="color: azure">Создание пользователя</h1>
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
<form class="form__input" name="user" action="/admin/users/createUser" method="post">
    <input  title="Логин" placeholder="Логин" type="text" name="login">
    <input  title="Пароль" placeholder="Пароль" type="password"  name="password">
    <input  type="hidden" name = "role" value="USER">
    <div>
    <input class="form_button"  type="submit" value="Создать"/>
    <input class="form_button"  type="reset" value="Отмена"/>
        </div>
</form>

</body>
</html>
