<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Driver info</title>

</head>
<body>
<h1>Информация по водителю</h1>
<table>
    <tr>
        <th>ID</th>
        <td>${driver.id}</td>
    </tr>
    <tr>
        <th>Имя</th>
        <td>${driver.firstName}</td>
    </tr>
    <tr>
        <th>Фамилия</th>
        <td>${driver.lastName}</td>
    </tr>
    <tr>
        <th>Телефон</th>
        <td>${driver.phoneNumber}</td>
    </tr>
    <tr>
        <th>Автомобиль</th>
        <td><select name="car" required form="updateDriver">
            <#list cars as car>
                <option value="${car.id}" <#if driver.carId == car.id>selected</#if> > ${car.model} ${car.number}</option>
            </#list>
        </select></td>
    </tr>
</table>
<br>
<a href="/drivers">Back</a>
<form id="updateDriver" action="/drivers/${driver.id}/update" method="post">

    <input type="submit" value="Сохранить" />
</form>
</body>
</html>