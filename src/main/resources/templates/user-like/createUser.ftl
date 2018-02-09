<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание нового Пользователя</title>

</head>
<body>

<form name="user" action="/admin/users/createUser" method="post">
    <p>Логин</p>
    <input title="Логин" type="text" name="login">
    <p>Пароль</p>
    <input title="Пароль" type="password" name="password">
    <input type="hidden" name = "role" value="USER">
    <p></p>
    <input type="submit" value="Создать">
    <p></p>
    <input type="reset" value="Отмена">
</form>

</body>
</html>