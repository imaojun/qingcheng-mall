<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
id:${student.id}&nbsp;&nbsp;
name:${student.name}&nbsp;&nbsp;
age:${student.age}&nbsp;&nbsp;
address:${student.address}&nbsp;&nbsp;

<table border="1">
    <tr>
        <th>i</th>
        <th>id</th>
        <th>name</th>
        <th>age</th>
        <th>address</th>
    </tr>
    <#list studentList as student>
        <#if student_index %2 ==0>
    <tr bgcolor="gray">
        <#else >
    <tr bgcolor="#ffe4c4">
        </#if>
        <td>${student_index}</td>
        <td>${student.id}</td>
        <td>${student.name}</td>
        <td>${student.age}</td>
        <td>${student.address}</td>
    </tr>
    </#list>
</table>
</body>
</html>