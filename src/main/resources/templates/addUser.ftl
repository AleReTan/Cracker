<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Создание нового заказа</title>

</head>
<body>

<form name="order" action="/addOrder" method="post">
    <p>Имя</p>
    <input title="Имя" type="text" name="firstName">
    <p>Фамилия</p>
    <input title="Фамилия" type="text" name="lastName">
    <p>Адрес</p>
    <input title="Адрес" type="text" name="address">
    <p>Телефон</p>
    <input title="Телефон" type="text" name="mobPhone">
    <p>Марка автомобиля</p>
    <input title="Марка автомобиля" type="text" name="modelCar">
    <p>Цвет</p>
    <input title="Цвет" type="text" name="color">
    <p>Номер автомобиля</p>
    <input title="Номер автомобиля" type="text" name="numberAuto">
    <p>Водитель</p>
    <input title="Водитель" type="text" name="drivers">
    <p>Тариф</p>
    <select name = "rate" required>
        <option value="Базовый">Базовый</option>
        <option value="Расширенный">Расширенный</option></select>
    <p>Стоимость</p>
    <input title="Стоимость" type="text" name="costOrder">
    <input type="submit" value="Создать">
</form>




<form action="obrabotka.php" METHOD="post">
    <table border="0" cellpadding="5" cellspacing="10">
        <tr>
            <td>Имя:</td>
            <td><input name="name_user" type="text" size="10"></td>
        </tr>
        <tr>
            <td>Пароль:</td>
            <td><input name="pass_user" type="password" size="10"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Отправить">
                <input type="reset" value="Очистить">
            </td>
        </tr>
    </table>
</form>








</body>
</html>