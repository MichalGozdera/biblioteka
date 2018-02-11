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

        input {
            font-size: inherit;
            height: 30px;
            float: right;
        }

        a {
            font-family: Comic Sans MS;
            color: yellow;
        }

        img {
            z-index: -1;
            position: absolute;
            opacity: 0.2;
        }

        #addBook {
            font-size: 25px;
            width: 60%;
        }
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
    <h3>Znajdź książkę</h3>
    <form id=addBook action="./books" method="POST">
        Title:
        <input type="text" name="title">
        </br>
        Author:
        <input type="text" name="author">
        </br>
        Category
        <input list="category" name="category">
        <datalist id="category">
            <option value="Liryka">
            <option value="Epika">
            <option value="Dramat">
        </datalist>
        </br>
        Year of release
        <input type="number" name="yearOfRelease" max="2018">
        </br>
        </br>
        <input type="submit" value="submit">
    </form>
    <!--content-->
    <c:if test="${not empty books}">
        <h3>Lista książek</h3>
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
                    <c:if test="${book.isLent() eq true}">
                        <td>Wypożyczona</td>
                        <c:if test="${sessionScope.get('user').getLogin()=='admin'}">
                            <td>
                                <form style="float:left" action="./return?id=${book.getBookId()}" method="POST">
                                    <input style="clear: both" type="submit" value="Zwróć książkę">
                                </form>
                            </td>
                        </c:if>
                    </c:if>
                    <c:if test="${book.isLent() eq false}">
                        <c:if test="${sessionScope.get('user').getLogin()!='admin'}">
                            <td><a style="float:left" href="./confirm?id=${book.getBookId()}">Wypożycz
                                książkę</a></td>
                        </c:if>
                    </c:if>

                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div style="clear: both"/>
</body>
</html>
