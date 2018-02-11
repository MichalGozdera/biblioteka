<%--
  Created by IntelliJ IDEA.
  User: Cokeman
  Date: 04.02.2018
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.get('user')==null}">
    <ul>
        <li><a href="./home">Strona główna</a></li>
        <li><a href="./register">Rejestracja</a></li>
        <li><a href="./login">Logowanie</a></li>
    </ul>
</c:if>
<c:if test="${sessionScope.get('user')!=null}">
    <ul>

    <li><a href="./home">Strona główna</a></li>

        <li>Witaj ${sessionScope.get('user').getName()}</li>

        <li><form style="float:left" action="./logout" method="POST">
            <input style="clear: both" type="submit" value="logout">
        </form></li>
    </br>
    </br>
        <c:if test="${sessionScope.get('user').getLogin()=='admin'}">
            <li><a style="float:left" href="./addBook">Dodaj książkę</a></li>
        </c:if>
        <li><a style="float:left" href="./books">Wyszukiwarka książek</a></li>
        <li><a style="float:left" href="./myBooks">Moje wypożyczenia</a></li>
        </ul>
</c:if>