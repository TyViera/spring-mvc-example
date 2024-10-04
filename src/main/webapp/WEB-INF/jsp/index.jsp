<%--
  Created by IntelliJ IDEA.
  User: naza.vieraipanaque
  Date: 10/2/2024
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
<p>Hello ${name}!</p>
<p>Header ${customerHeader}!</p>

<%
    var books = (List<String>) request.getAttribute("books");
    if (books == null || books.isEmpty()) {
        out.println("Oops, you do not have books");
    } else {
        out.println("<p>Your books are:</p>");
        out.println("<ul>");
        for (var book : books) {
            out.println("<li>" + book + "</li>");
        }
        out.println("</ul>");
    }
%>
</body>
</html>
