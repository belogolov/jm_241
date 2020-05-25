<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>

<body>
<div>
    <h1>Hello, ${user.firstName}!</h1>
</div>

<div>
    <p>Name: ${user.firstName}</p>
    <h1></h1>
    <p>E-mail: ${user.lastName}</p>
    <h1></h1>
    <p>E-mail: ${user.email}</p>
    <h1></h1>
    <p>Password: ${user.password}</p>
    <h1></h1>
    <p>Role: ${user.role}</p>
</div>

</body>
</html>

