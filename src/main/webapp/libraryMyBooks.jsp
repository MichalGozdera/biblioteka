<%@ page import="com.sda.biblioteka.servlets.spring.db.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Cokeman
  Date: 04.02.2018
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            font-family: Comic Sans MS;
            background-color: black;
            color: yellow;

        }

        a {
            font-family: Comic Sans MS;
            color: yellow;
        }

        img {
            z-index: -1;
            position: absolute;
            opacity: 0.2;
    </style>
    <title>Biblioteka SDA ŁÓDŹ</title>
</head>
<body>

<img src="http://mylittleponyn6.blog.pl/files/2015/02/rainbow_dash_s_hot_minute_by_mrlolcats17-d5lo21h.png">

<div style="float:left;width:  30%">
    <%@include file="menu.jsp" %>


</div>
<div style="float:right; width:  70%">
    <h2>Witaj na stronie biblioteki SDA</h2>

    <!--content-->
    <h3>Moje wypożyczone książki</h3>
    <!--do zrobienia gdy books=null-->

    <table>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Category</th>
            <th>Year of release</th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.getTitle()}</td>
                <td>${book.getAuthor()}</td>
                <td>${book.getCategory()}</td>
                <td>${book.getYearOfRelease()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="clear: both"/>
</body>
</html>
