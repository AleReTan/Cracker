<!DOCTYPE html>
<html>
<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<h1>Persons list</h1>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Age</th>
    </tr>
<#list persons as person>
    <tr>
        <td><a href="/persons/${person.id}">${person.id}</a></td>
        <td>${person.name}</td>
        <td>${person.email}</td>
        <td>${person.age}</td>
    </tr>
</#list>
</table>
<a href="/addPerson">Create user</a>
</body>
</html>
