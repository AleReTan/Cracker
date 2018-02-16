<html>
<body>
<h1>Cars list</h1>
<table>
    <tr>
        <th>Название</th>
        <th>Номер</th>
        <th>Модель</th>
    </tr>
<#list cars as car>
    <tr>
        <!--<td>${car.id}</td>-->
        <td><a href="/cars/${car.id}">${car.name}</a></td>
        <td>${car.number}</td>
        <td>${car.model}</td>
    </tr>
</#list>
</table>
<a href="/cars/addCar">Create cars</a>
<h2> </h2>

</body>
</html>
