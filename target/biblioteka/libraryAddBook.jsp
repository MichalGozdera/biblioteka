<%@ page import="com.sda.biblioteka.servlets.spring.db.UserContext" %>
<%@ page import="com.sda.biblioteka.servlets.spring.db.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Cokeman
  Date: 04.02.2018
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblioteka SDA ŁÓDŹ DODAWANIE KSIĄŻKI</title>
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
        input{
            font-size: inherit;
            height: 35px;
            float: right;
        }
        img {
            z-index: -1;
            position: absolute;
            opacity: 0.2;
        }
        #addBook{
            font-size: 30px;
            position: absolute;
            top:30%;
            left:40%;
            width: auto;
        }
    </style>
</head>
<img src="http://mylittleponyn6.blog.pl/files/2015/02/rainbow_dash_s_hot_minute_by_mrlolcats17-d5lo21h.png">

<body>

<div style="float:left;width:  30%;">
    <%@include file="menu.jsp"%>
</div>
<div style="float:right; width: 70%; text-align: center">
    <h2>Witaj na stronie biblioteki SDA</h2>
    </br>
    <div id="addBook">
        <%
           if(request.getHeader("Referer")==null){
               response.sendRedirect("https://www.youtube.com/watch?v=LT3kI3BsxlQ");
           }
        %>
        <form action="./addBook" method="POST">
            Title:
            <input type="text" name="title" required>
            </br>
            Author:
            <input type="text" name="author" required>
            </br>
            Category
            <input list="category" name="category" required>
            <datalist id="category">
                <option value="Liryka">
                <option value="Epika">
                <option value="Dramat">
            </datalist>
            </br>
            Year of release
            <input type="number" name="yearOfRelease" max="2018" required>
            </br>
            <input type="submit" value="submit">
        </form>
    </div>
    <!--content-->
</div>
<div style="clear: both"/>
</body>
</html>
