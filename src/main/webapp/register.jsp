<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2024/3/18
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>

<%@include file="header.jsp" %>

<form method="post" action="register">
    username<input type="text" name="username"><br>
    password<input type="password" name="password"><br>
    Email<input type="text" name="email"><br>
    Gender:<input type="radio" name="gender" value="Male">Male<input type="radio" name="gender" value="Female">Female<br>
    Date of Birth :<input type="text" name="birthday"><br>
    <input type="submit" value="Register">
</form>

<%@include file="footer.jsp"%>