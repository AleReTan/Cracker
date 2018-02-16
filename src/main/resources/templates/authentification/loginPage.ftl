<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<form name="Authentificate" action="/auth" method="post">
    <input type="text" name = "status" value="${status}">
    <p>Имя</p>
    <input title="login" type="text" name="login">
    <p>Фамилия</p>
    <input title="password" type="password" name="password">
    <input type="submit" value="Войти">
</form>

</body>
</html>