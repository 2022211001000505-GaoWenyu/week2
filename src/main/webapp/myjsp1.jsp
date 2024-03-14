<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2024/3/14
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>week2-exercise2</title>
</head>
<body>
<p>New Uers Registration!</p>
<form>
    <input type = "text" name="Username"  placeholder="Username"><br>
    <input type="text"name="password" placeholder="password" maxlength="8"><br>
    <input type="text" name="Email" placeholder="Email"><br>
    Gender <input type="radio" name="sex" value="boy">Male <input type="radio" value="girl" name="sex">Female<br>
    <input type="date" name="birthday"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
