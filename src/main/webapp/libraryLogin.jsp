<%@ page import="com.sda.biblioteka.servlets.spring.db.UserContext" %>
<%@ page import="com.sda.biblioteka.servlets.spring.db.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Cokeman
  Date: 04.02.2018
  Time: 09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Biblioteka SDA ŁÓDŹ LOGOWANIE</title>
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
        #log{
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
<div style="float:left;width:  30%">
    <%@include file="menu.jsp"%>
</div>
<div style="float:right; width: 70%; text-align: center">
    <h2>Witaj na stronie biblioteki SDA</h2>
</br>
    <div id="log">
        <%
            String param1 = request.getParameter("login");
            if (param1 != null) {
                if (param1.length() > 4) {
                    out.println("<p>User " + param1 + " succesfully registered</p>\n" +
                            "    <p>Please login</p>");
                } else if (param1.equals("invP")) {
                    out.println("<p>Invalid password</p>\n" +
                            "    <p>Please try again</p>");
                } else if (param1.equals("invL")) {
                    out.println("<p>Invalid login</p>\n" +
                            "    <p>Please try again</p>");
                }
            }
        %>
        <form action="./login" method="POST">
            name:
            <input type="text" name="login" required="" pattern=".{5,}" title="Must contain at least 5 characters">
            <br>
            password:
            <input type="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                   title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters">
            <br>
            <input type="submit" value="submit">
        </form>
    </div>
    <!--content-->
</div>
<div style="clear: both"/>
</body>
</html>
