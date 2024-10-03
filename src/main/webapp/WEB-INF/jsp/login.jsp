<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: naza.vieraipanaque
  Date: 10/3/2024
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>

<div>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input id="username" type="text" name="username"/>
        <br/>
        <label for="password">Password:</label>
        <input id="password" type="password" name="password"/>
        <br/>
        <input type="submit">
    </form>
    <%
        var errors = (List<String>) request.getAttribute("errors");
        if (errors != null) {
            out.println("<ul>");
            for (var error : errors) {
                out.println("<li>" + error + "</li>");
            }
            out.println("</ul>");
        }
    %>
</div>

</body>
</html>
