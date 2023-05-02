<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dani
  Date: 02/05/2023
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:if test="${empty usuario}">
    <jsp:forward page="/" />
</c:if>


<table style="border: 0px; width: 100% ">
    <tr>
        <td>${pageContext.session.id}</td>
        <td>Bienvenido, ${usuario.email} </td>
        <td><a href="/logout">Salir</a></td>
    </tr>
</table>
<p>


</p>
