<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="authorisation.js" type="text/javascript"></script>
</head>
<body>

<form name="Authentificate" action="/login" method="post">
    <p>Логин</p>
    <input title="login" type="text" id="login" name="login">
    <p>Пароль</p>
    <input title="password" type="password" id="password" name="password">
    <input type="hidden" name = "role" id="role" value="USER">

    <input type="submit" value="Войти" id = "auth">
</form>

</body>
</html>