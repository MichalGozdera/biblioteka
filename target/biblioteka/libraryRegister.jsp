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
    <title>Biblioteka SDA ŁÓDŹ REJESTRACJA</title>
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
        #register{
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
    <div id="register">
        <%
            UserContext userContext=new UserContext();
            if(userContext.findUser("admin")==null) {
                User admin = new User("Administrator", "admin", "admin@admin.pl", "admin", "Admin2018","2018-02-04");
                userContext.addUser(admin);
            }
            String param1=request.getParameter("login");
            if(param1!=null){
                out.println("<p>User "+ param1+" already exists</p>\n" +
                        "    <p>Try different login</p>");
            }
        %>
        <form action="./register" name="registerForm" method="POST">
            name:
            <input type="text" name="name" required=>
            <br>
            last name:
            <input type="text" name="lastName" required>
            <br>
            email
            <input type="email" name="email" required>
            <br>
            login
            <input type="text" name="login" required pattern=".{5,}" title="Must contain at least 5 characters">
            <br>
            password
            <input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
            <br>
            date of birth
            <input type="date" name="birthdate" required max="2000-02-01">
            <br>
            <input type="submit" value="submit">
        </form>
    </div>
    <!--content-->
</div>
<div style="clear: both"/>
</body>
</html>
