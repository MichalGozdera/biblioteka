<%@ page import="com.sda.biblioteka.servlets.spring.db.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: Cokeman
  Date: 04.02.2018
  Time: 09:17
  To change this template use File | Settings | File Templates.
--%>
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
    <%@include file="menu.jsp"%>


</div>
<div style="float:right; width:  70%; text-align: center">
    <h2>Potwierdzenie</h2>
    <h3>Czy na pewno chcesz wypożyczyć tę książkę?</h3>

    <p><strong>Tytuł</strong> ${book.title}</p>
    <p><strong>Autor</strong> ${book.author}</p>
    <p><strong>Kategoria</strong> ${book.category}</p>
    <p><strong>Rok Wydania</strong> ${book.yearOfRelease}</p>
    <form action="./borrow?id=${book.bookId}" method="POST">
        <button type="submit">Tak</button>
        <button onclick="location.href='./books'" type="button">
            Nie</button>
    </form>

    <!--content-->
</div>
<div style="clear: both"/>
</body>
</html>
