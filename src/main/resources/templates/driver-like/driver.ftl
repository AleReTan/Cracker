<!DOCTYPE html>
<html lang="rus">
<head>
    <title>Driver info</title>
    <script>
        // This variable can be accessed from js
        var selectedCarId = "${(selectedCar.id)!}";
    </script>
    <script type="text/javascript" src="/js/driverScript.js"></script>
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
        <th>Геоданные</th>
        <td>${(driver.driverGeoData)!"0.0,0.0"}</td>
    </tr>
    <tr>
        <th>Автомобиль</th>
       <!-- <td><select name="carId" required form="updateDriver">
            <option value="${driver.carId}" selected>Выберите машину</option>
            <#list cars as car>
                <option value="${car.id}"
                        <#if driver.carId == car.id>selected</#if> > ${car.model} ${car.number}</option>
            </#list>
        </select></td>-->

        <td><select name="carId" id="driverId" form="updateDriver">
        <#if (selectedCar.id)??>
            <option value="${(selectedCar.id)}"
                    selected>${(selectedCar.model)} ${(selectedCar.number)}</option>
        </#if>
        <#list cars as car>
            <option value="${car.id}"
                    <#if driver.carId == car.id>selected</#if> > ${car.model} ${car.number}</option>
        </#list>
        </select></td>

    </tr>
</table>
<br>
<a href="/drivers">Back</a>
<form id="updateDriver" action="/drivers/${driver.id}" method="post">
    <input type="hidden" name="_method" value="patch"/>
    <input type="hidden" name = "name" value="${driver.name}">
    <input type="hidden" name = "typeId" value="${driver.typeId}">
    <input type="hidden" name = "firstName" value="${driver.firstName}">
    <input type="hidden" name = "lastName" value="${driver.lastName}">
    <input type="hidden" name = "phoneNumber" value="${driver.phoneNumber}">
    <input type="hidden" name = "driverGeoData" value="${(driver.driverGeoData)!"0.0,0.0"}">
    <input type="hidden" name = "onShift" value="${(driver.onShift)}">
    <input type="hidden" name = "login" value="${(driver.login)}">
    <input type="submit" id="saveButton" disabled value="Сохранить" />
</form>
<form id="deleteDriver" action="/drivers/${driver.id}" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="Удалить" />
</form>
</body>
</html>
