<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dispath service of towling</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/css/formcreate.css">
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
            <li><a onclick="location.href='/orders'">Заказы</a></li>
            <li><a onclick="location.href='/drivers'">Водители</a></li>
            <li><a onclick="location.href='/admin/users'">Пользователи</a></li>
            <li><a onclick="location.href='/cars'">Машины</a></li>
            <li><a onclick="location.href='/logout'">Выйти</a></li>
        </ul>
    </nav>
</div>
<form name="user" action="/admin/users/createUser" method="post">
    <div>
        <input title="Логин содержит латинские символы, минимум 3" placeholder="Логин" class="form__input" pattern="[A-Za-z]{3,}" type="text" name="login"  />
    </div>
    <div>
        <input title="Пароль должен содержать 6 символов" placeholder="Пароль" required type="password" name="password" maxlength="6" minlength="6" pattern="[0-9A-za-zА-Яа-яЁё]{6,}">
    </div>
    <br/>
    <div>
        <select name = "role">
            <option value="ADMIN">ADMIN</option>
            <option value="USER">USER</option>
            <option value="DRIVER">DRIVER</option>
            <option value="CUSTOMER">CUSTOMER</option>
        </select>
    </div>
    <div>
        <input class="form_button" type="submit" value="Создать"/>
        <input class="form_button" type="reset" value="Отмена"/>
    </div>
</form>

</body>
</html>
